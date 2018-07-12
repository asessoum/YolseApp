/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { UtiProfilMySuffixDetailComponent } from 'app/entities/uti-profil-my-suffix/uti-profil-my-suffix-detail.component';
import { UtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';

describe('Component Tests', () => {
    describe('UtiProfilMySuffix Management Detail Component', () => {
        let comp: UtiProfilMySuffixDetailComponent;
        let fixture: ComponentFixture<UtiProfilMySuffixDetailComponent>;
        const route = ({ data: of({ utiProfil: new UtiProfilMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [UtiProfilMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UtiProfilMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtiProfilMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.utiProfil).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
