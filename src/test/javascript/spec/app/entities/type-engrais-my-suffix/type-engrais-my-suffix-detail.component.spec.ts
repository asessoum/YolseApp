/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { TypeEngraisMySuffixDetailComponent } from 'app/entities/type-engrais-my-suffix/type-engrais-my-suffix-detail.component';
import { TypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';

describe('Component Tests', () => {
    describe('TypeEngraisMySuffix Management Detail Component', () => {
        let comp: TypeEngraisMySuffixDetailComponent;
        let fixture: ComponentFixture<TypeEngraisMySuffixDetailComponent>;
        const route = ({ data: of({ typeEngrais: new TypeEngraisMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TypeEngraisMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeEngraisMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeEngraisMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeEngrais).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
