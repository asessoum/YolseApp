/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YolseAppTestModule } from '../../../test.module';
import { EngraisMySuffixComponent } from 'app/entities/engrais-my-suffix/engrais-my-suffix.component';
import { EngraisMySuffixService } from 'app/entities/engrais-my-suffix/engrais-my-suffix.service';
import { EngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';

describe('Component Tests', () => {
    describe('EngraisMySuffix Management Component', () => {
        let comp: EngraisMySuffixComponent;
        let fixture: ComponentFixture<EngraisMySuffixComponent>;
        let service: EngraisMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [EngraisMySuffixComponent],
                providers: []
            })
                .overrideTemplate(EngraisMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EngraisMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EngraisMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EngraisMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.engrais[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
