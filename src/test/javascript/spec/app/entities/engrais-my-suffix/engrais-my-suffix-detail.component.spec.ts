/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { EngraisMySuffixDetailComponent } from 'app/entities/engrais-my-suffix/engrais-my-suffix-detail.component';
import { EngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';

describe('Component Tests', () => {
    describe('EngraisMySuffix Management Detail Component', () => {
        let comp: EngraisMySuffixDetailComponent;
        let fixture: ComponentFixture<EngraisMySuffixDetailComponent>;
        const route = ({ data: of({ engrais: new EngraisMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [EngraisMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EngraisMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EngraisMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.engrais).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
