/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { BesoinEngraisMySuffixDetailComponent } from 'app/entities/besoin-engrais-my-suffix/besoin-engrais-my-suffix-detail.component';
import { BesoinEngraisMySuffix } from 'app/shared/model/besoin-engrais-my-suffix.model';

describe('Component Tests', () => {
    describe('BesoinEngraisMySuffix Management Detail Component', () => {
        let comp: BesoinEngraisMySuffixDetailComponent;
        let fixture: ComponentFixture<BesoinEngraisMySuffixDetailComponent>;
        const route = ({ data: of({ besoinEngrais: new BesoinEngraisMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [BesoinEngraisMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BesoinEngraisMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BesoinEngraisMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.besoinEngrais).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
