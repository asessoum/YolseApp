/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YolseAppTestModule } from '../../../test.module';
import { ProfilMySuffixComponent } from 'app/entities/profil-my-suffix/profil-my-suffix.component';
import { ProfilMySuffixService } from 'app/entities/profil-my-suffix/profil-my-suffix.service';
import { ProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';

describe('Component Tests', () => {
    describe('ProfilMySuffix Management Component', () => {
        let comp: ProfilMySuffixComponent;
        let fixture: ComponentFixture<ProfilMySuffixComponent>;
        let service: ProfilMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [ProfilMySuffixComponent],
                providers: []
            })
                .overrideTemplate(ProfilMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfilMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProfilMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.profils[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
