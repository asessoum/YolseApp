/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YolseAppTestModule } from '../../../test.module';
import { CultureMySuffixComponent } from 'app/entities/culture-my-suffix/culture-my-suffix.component';
import { CultureMySuffixService } from 'app/entities/culture-my-suffix/culture-my-suffix.service';
import { CultureMySuffix } from 'app/shared/model/culture-my-suffix.model';

describe('Component Tests', () => {
    describe('CultureMySuffix Management Component', () => {
        let comp: CultureMySuffixComponent;
        let fixture: ComponentFixture<CultureMySuffixComponent>;
        let service: CultureMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [CultureMySuffixComponent],
                providers: []
            })
                .overrideTemplate(CultureMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CultureMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CultureMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CultureMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.cultures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
