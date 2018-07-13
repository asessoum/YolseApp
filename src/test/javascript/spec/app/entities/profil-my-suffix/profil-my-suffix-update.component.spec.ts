/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { ProfilMySuffixUpdateComponent } from 'app/entities/profil-my-suffix/profil-my-suffix-update.component';
import { ProfilMySuffixService } from 'app/entities/profil-my-suffix/profil-my-suffix.service';
import { ProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';

describe('Component Tests', () => {
    describe('ProfilMySuffix Management Update Component', () => {
        let comp: ProfilMySuffixUpdateComponent;
        let fixture: ComponentFixture<ProfilMySuffixUpdateComponent>;
        let service: ProfilMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [ProfilMySuffixUpdateComponent]
            })
                .overrideTemplate(ProfilMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfilMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProfilMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.profil = entity;
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
                    const entity = new ProfilMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.profil = entity;
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
