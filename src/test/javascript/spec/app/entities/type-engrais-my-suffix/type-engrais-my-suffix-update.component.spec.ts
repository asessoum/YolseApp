/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { TypeEngraisMySuffixUpdateComponent } from 'app/entities/type-engrais-my-suffix/type-engrais-my-suffix-update.component';
import { TypeEngraisMySuffixService } from 'app/entities/type-engrais-my-suffix/type-engrais-my-suffix.service';
import { TypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';

describe('Component Tests', () => {
    describe('TypeEngraisMySuffix Management Update Component', () => {
        let comp: TypeEngraisMySuffixUpdateComponent;
        let fixture: ComponentFixture<TypeEngraisMySuffixUpdateComponent>;
        let service: TypeEngraisMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TypeEngraisMySuffixUpdateComponent]
            })
                .overrideTemplate(TypeEngraisMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeEngraisMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeEngraisMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TypeEngraisMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.typeEngrais = entity;
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
                    const entity = new TypeEngraisMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.typeEngrais = entity;
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
