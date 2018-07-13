/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { UtiProfilMySuffixUpdateComponent } from 'app/entities/uti-profil-my-suffix/uti-profil-my-suffix-update.component';
import { UtiProfilMySuffixService } from 'app/entities/uti-profil-my-suffix/uti-profil-my-suffix.service';
import { UtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';

describe('Component Tests', () => {
    describe('UtiProfilMySuffix Management Update Component', () => {
        let comp: UtiProfilMySuffixUpdateComponent;
        let fixture: ComponentFixture<UtiProfilMySuffixUpdateComponent>;
        let service: UtiProfilMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [UtiProfilMySuffixUpdateComponent]
            })
                .overrideTemplate(UtiProfilMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UtiProfilMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtiProfilMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new UtiProfilMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.utiProfil = entity;
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
                    const entity = new UtiProfilMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.utiProfil = entity;
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
