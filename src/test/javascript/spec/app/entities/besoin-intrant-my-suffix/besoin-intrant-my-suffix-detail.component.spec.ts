/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { BesoinIntrantMySuffixDetailComponent } from 'app/entities/besoin-intrant-my-suffix/besoin-intrant-my-suffix-detail.component';
import { BesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';

describe('Component Tests', () => {
    describe('BesoinIntrantMySuffix Management Detail Component', () => {
        let comp: BesoinIntrantMySuffixDetailComponent;
        let fixture: ComponentFixture<BesoinIntrantMySuffixDetailComponent>;
        const route = ({ data: of({ besoinIntrant: new BesoinIntrantMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [BesoinIntrantMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BesoinIntrantMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BesoinIntrantMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.besoinIntrant).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
