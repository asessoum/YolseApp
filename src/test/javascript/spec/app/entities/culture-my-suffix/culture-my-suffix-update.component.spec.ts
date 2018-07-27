/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { CultureMySuffixUpdateComponent } from 'app/entities/culture-my-suffix/culture-my-suffix-update.component';
import { CultureMySuffixService } from 'app/entities/culture-my-suffix/culture-my-suffix.service';
import { CultureMySuffix } from 'app/shared/model/culture-my-suffix.model';

describe('Component Tests', () => {
    describe('CultureMySuffix Management Update Component', () => {
        let comp: CultureMySuffixUpdateComponent;
        let fixture: ComponentFixture<CultureMySuffixUpdateComponent>;
        let service: CultureMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [CultureMySuffixUpdateComponent]
            })
                .overrideTemplate(CultureMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CultureMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CultureMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CultureMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.culture = entity;
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
                    const entity = new CultureMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.culture = entity;
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
