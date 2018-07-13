/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { ProvinceMySuffixUpdateComponent } from 'app/entities/province-my-suffix/province-my-suffix-update.component';
import { ProvinceMySuffixService } from 'app/entities/province-my-suffix/province-my-suffix.service';
import { ProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';

describe('Component Tests', () => {
    describe('ProvinceMySuffix Management Update Component', () => {
        let comp: ProvinceMySuffixUpdateComponent;
        let fixture: ComponentFixture<ProvinceMySuffixUpdateComponent>;
        let service: ProvinceMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [ProvinceMySuffixUpdateComponent]
            })
                .overrideTemplate(ProvinceMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProvinceMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvinceMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProvinceMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.province = entity;
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
                    const entity = new ProvinceMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.province = entity;
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
