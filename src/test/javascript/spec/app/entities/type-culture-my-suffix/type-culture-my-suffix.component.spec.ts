/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YolseAppTestModule } from '../../../test.module';
import { TypeCultureMySuffixComponent } from 'app/entities/type-culture-my-suffix/type-culture-my-suffix.component';
import { TypeCultureMySuffixService } from 'app/entities/type-culture-my-suffix/type-culture-my-suffix.service';
import { TypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';

describe('Component Tests', () => {
    describe('TypeCultureMySuffix Management Component', () => {
        let comp: TypeCultureMySuffixComponent;
        let fixture: ComponentFixture<TypeCultureMySuffixComponent>;
        let service: TypeCultureMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TypeCultureMySuffixComponent],
                providers: []
            })
                .overrideTemplate(TypeCultureMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeCultureMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeCultureMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TypeCultureMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.typeCultures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
