/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { UtilisateurMySuffixUpdateComponent } from 'app/entities/utilisateur-my-suffix/utilisateur-my-suffix-update.component';
import { UtilisateurMySuffixService } from 'app/entities/utilisateur-my-suffix/utilisateur-my-suffix.service';
import { UtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';

describe('Component Tests', () => {
    describe('UtilisateurMySuffix Management Update Component', () => {
        let comp: UtilisateurMySuffixUpdateComponent;
        let fixture: ComponentFixture<UtilisateurMySuffixUpdateComponent>;
        let service: UtilisateurMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [UtilisateurMySuffixUpdateComponent]
            })
                .overrideTemplate(UtilisateurMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UtilisateurMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisateurMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new UtilisateurMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.utilisateur = entity;
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
                    const entity = new UtilisateurMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.utilisateur = entity;
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
