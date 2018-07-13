/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { EngraisClientMySuffixUpdateComponent } from 'app/entities/engrais-client-my-suffix/engrais-client-my-suffix-update.component';
import { EngraisClientMySuffixService } from 'app/entities/engrais-client-my-suffix/engrais-client-my-suffix.service';
import { EngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';

describe('Component Tests', () => {
    describe('EngraisClientMySuffix Management Update Component', () => {
        let comp: EngraisClientMySuffixUpdateComponent;
        let fixture: ComponentFixture<EngraisClientMySuffixUpdateComponent>;
        let service: EngraisClientMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [EngraisClientMySuffixUpdateComponent]
            })
                .overrideTemplate(EngraisClientMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EngraisClientMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EngraisClientMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EngraisClientMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.engraisClient = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EngraisClientMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.engraisClient = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
