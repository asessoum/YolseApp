/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { EngraisMySuffixUpdateComponent } from 'app/entities/engrais-my-suffix/engrais-my-suffix-update.component';
import { EngraisMySuffixService } from 'app/entities/engrais-my-suffix/engrais-my-suffix.service';
import { EngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';

describe('Component Tests', () => {
    describe('EngraisMySuffix Management Update Component', () => {
        let comp: EngraisMySuffixUpdateComponent;
        let fixture: ComponentFixture<EngraisMySuffixUpdateComponent>;
        let service: EngraisMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [EngraisMySuffixUpdateComponent]
            })
                .overrideTemplate(EngraisMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EngraisMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EngraisMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EngraisMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.engrais = entity;
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
                    const entity = new EngraisMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.engrais = entity;
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
