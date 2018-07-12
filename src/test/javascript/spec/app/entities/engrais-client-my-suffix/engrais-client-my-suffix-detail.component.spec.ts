/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { YolseAppTestModule } from '../../../test.module';
import { EngraisClientMySuffixDetailComponent } from 'app/entities/engrais-client-my-suffix/engrais-client-my-suffix-detail.component';
import { EngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';

describe('Component Tests', () => {
    describe('EngraisClientMySuffix Management Detail Component', () => {
        let comp: EngraisClientMySuffixDetailComponent;
        let fixture: ComponentFixture<EngraisClientMySuffixDetailComponent>;
        const route = ({ data: of({ engraisClient: new EngraisClientMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [EngraisClientMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EngraisClientMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EngraisClientMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.engraisClient).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
