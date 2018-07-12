/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { ProfilMySuffixDetailComponent } from 'app/entities/profil-my-suffix/profil-my-suffix-detail.component';
import { ProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';

describe('Component Tests', () => {
    describe('ProfilMySuffix Management Detail Component', () => {
        let comp: ProfilMySuffixDetailComponent;
        let fixture: ComponentFixture<ProfilMySuffixDetailComponent>;
        const route = ({ data: of({ profil: new ProfilMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [ProfilMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProfilMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.profil).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
