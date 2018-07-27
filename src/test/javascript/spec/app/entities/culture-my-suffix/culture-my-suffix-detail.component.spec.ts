/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { CultureMySuffixDetailComponent } from 'app/entities/culture-my-suffix/culture-my-suffix-detail.component';
import { CultureMySuffix } from 'app/shared/model/culture-my-suffix.model';

describe('Component Tests', () => {
    describe('CultureMySuffix Management Detail Component', () => {
        let comp: CultureMySuffixDetailComponent;
        let fixture: ComponentFixture<CultureMySuffixDetailComponent>;
        const route = ({ data: of({ culture: new CultureMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [CultureMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CultureMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CultureMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.culture).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
