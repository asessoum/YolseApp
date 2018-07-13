/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { SuiviChampsMySuffixDetailComponent } from 'app/entities/suivi-champs-my-suffix/suivi-champs-my-suffix-detail.component';
import { SuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';

describe('Component Tests', () => {
    describe('SuiviChampsMySuffix Management Detail Component', () => {
        let comp: SuiviChampsMySuffixDetailComponent;
        let fixture: ComponentFixture<SuiviChampsMySuffixDetailComponent>;
        const route = ({ data: of({ suiviChamps: new SuiviChampsMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [SuiviChampsMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SuiviChampsMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SuiviChampsMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.suiviChamps).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
