/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { ProvinceMySuffixDetailComponent } from 'app/entities/province-my-suffix/province-my-suffix-detail.component';
import { ProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';

describe('Component Tests', () => {
    describe('ProvinceMySuffix Management Detail Component', () => {
        let comp: ProvinceMySuffixDetailComponent;
        let fixture: ComponentFixture<ProvinceMySuffixDetailComponent>;
        const route = ({ data: of({ province: new ProvinceMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [ProvinceMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProvinceMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProvinceMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.province).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
