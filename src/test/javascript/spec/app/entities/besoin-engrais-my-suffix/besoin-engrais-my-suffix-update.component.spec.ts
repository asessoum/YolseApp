/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { BesoinEngraisMySuffixUpdateComponent } from 'app/entities/besoin-engrais-my-suffix/besoin-engrais-my-suffix-update.component';
import { BesoinEngraisMySuffixService } from 'app/entities/besoin-engrais-my-suffix/besoin-engrais-my-suffix.service';
import { BesoinEngraisMySuffix } from 'app/shared/model/besoin-engrais-my-suffix.model';

describe('Component Tests', () => {
    describe('BesoinEngraisMySuffix Management Update Component', () => {
        let comp: BesoinEngraisMySuffixUpdateComponent;
        let fixture: ComponentFixture<BesoinEngraisMySuffixUpdateComponent>;
        let service: BesoinEngraisMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [BesoinEngraisMySuffixUpdateComponent]
            })
                .overrideTemplate(BesoinEngraisMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BesoinEngraisMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BesoinEngraisMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BesoinEngraisMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.besoinEngrais = entity;
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
                    const entity = new BesoinEngraisMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.besoinEngrais = entity;
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
