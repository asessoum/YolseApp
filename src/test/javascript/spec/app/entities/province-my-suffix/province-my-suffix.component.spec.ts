/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YolseAppTestModule } from '../../../test.module';
import { ProvinceMySuffixComponent } from 'app/entities/province-my-suffix/province-my-suffix.component';
import { ProvinceMySuffixService } from 'app/entities/province-my-suffix/province-my-suffix.service';
import { ProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';

describe('Component Tests', () => {
    describe('ProvinceMySuffix Management Component', () => {
        let comp: ProvinceMySuffixComponent;
        let fixture: ComponentFixture<ProvinceMySuffixComponent>;
        let service: ProvinceMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [ProvinceMySuffixComponent],
                providers: []
            })
                .overrideTemplate(ProvinceMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProvinceMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvinceMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProvinceMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.provinces[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
