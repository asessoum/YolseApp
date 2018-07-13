/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { SuiviChampsMySuffixUpdateComponent } from 'app/entities/suivi-champs-my-suffix/suivi-champs-my-suffix-update.component';
import { SuiviChampsMySuffixService } from 'app/entities/suivi-champs-my-suffix/suivi-champs-my-suffix.service';
import { SuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';

describe('Component Tests', () => {
    describe('SuiviChampsMySuffix Management Update Component', () => {
        let comp: SuiviChampsMySuffixUpdateComponent;
        let fixture: ComponentFixture<SuiviChampsMySuffixUpdateComponent>;
        let service: SuiviChampsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [SuiviChampsMySuffixUpdateComponent]
            })
                .overrideTemplate(SuiviChampsMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SuiviChampsMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuiviChampsMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SuiviChampsMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.suiviChamps = entity;
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
                    const entity = new SuiviChampsMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.suiviChamps = entity;
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
