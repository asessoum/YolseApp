/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YolseAppTestModule } from '../../../test.module';
import { UtiProfilMySuffixComponent } from 'app/entities/uti-profil-my-suffix/uti-profil-my-suffix.component';
import { UtiProfilMySuffixService } from 'app/entities/uti-profil-my-suffix/uti-profil-my-suffix.service';
import { UtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';

describe('Component Tests', () => {
    describe('UtiProfilMySuffix Management Component', () => {
        let comp: UtiProfilMySuffixComponent;
        let fixture: ComponentFixture<UtiProfilMySuffixComponent>;
        let service: UtiProfilMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [UtiProfilMySuffixComponent],
                providers: []
            })
                .overrideTemplate(UtiProfilMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UtiProfilMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtiProfilMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new UtiProfilMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.utiProfils[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
