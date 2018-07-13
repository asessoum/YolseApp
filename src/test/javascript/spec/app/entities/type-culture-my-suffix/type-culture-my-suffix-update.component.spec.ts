/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { TypeCultureMySuffixUpdateComponent } from 'app/entities/type-culture-my-suffix/type-culture-my-suffix-update.component';
import { TypeCultureMySuffixService } from 'app/entities/type-culture-my-suffix/type-culture-my-suffix.service';
import { TypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';

describe('Component Tests', () => {
    describe('TypeCultureMySuffix Management Update Component', () => {
        let comp: TypeCultureMySuffixUpdateComponent;
        let fixture: ComponentFixture<TypeCultureMySuffixUpdateComponent>;
        let service: TypeCultureMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TypeCultureMySuffixUpdateComponent]
            })
                .overrideTemplate(TypeCultureMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeCultureMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeCultureMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TypeCultureMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.typeCulture = entity;
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
                    const entity = new TypeCultureMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.typeCulture = entity;
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
