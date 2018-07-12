/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { TypeCultureMySuffixDetailComponent } from 'app/entities/type-culture-my-suffix/type-culture-my-suffix-detail.component';
import { TypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';

describe('Component Tests', () => {
    describe('TypeCultureMySuffix Management Detail Component', () => {
        let comp: TypeCultureMySuffixDetailComponent;
        let fixture: ComponentFixture<TypeCultureMySuffixDetailComponent>;
        const route = ({ data: of({ typeCulture: new TypeCultureMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TypeCultureMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeCultureMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeCultureMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeCulture).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
