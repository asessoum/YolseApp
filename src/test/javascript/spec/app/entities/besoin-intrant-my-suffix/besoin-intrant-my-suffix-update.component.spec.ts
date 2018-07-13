/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { BesoinIntrantMySuffixUpdateComponent } from 'app/entities/besoin-intrant-my-suffix/besoin-intrant-my-suffix-update.component';
import { BesoinIntrantMySuffixService } from 'app/entities/besoin-intrant-my-suffix/besoin-intrant-my-suffix.service';
import { BesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';

describe('Component Tests', () => {
    describe('BesoinIntrantMySuffix Management Update Component', () => {
        let comp: BesoinIntrantMySuffixUpdateComponent;
        let fixture: ComponentFixture<BesoinIntrantMySuffixUpdateComponent>;
        let service: BesoinIntrantMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [BesoinIntrantMySuffixUpdateComponent]
            })
                .overrideTemplate(BesoinIntrantMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BesoinIntrantMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BesoinIntrantMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BesoinIntrantMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.besoinIntrant = entity;
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
                    const entity = new BesoinIntrantMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.besoinIntrant = entity;
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
