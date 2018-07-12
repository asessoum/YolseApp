/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { YolseAppTestModule } from '../../../test.module';
import { TypeEngraisMySuffixComponent } from 'app/entities/type-engrais-my-suffix/type-engrais-my-suffix.component';
import { TypeEngraisMySuffixService } from 'app/entities/type-engrais-my-suffix/type-engrais-my-suffix.service';
import { TypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';

describe('Component Tests', () => {
    describe('TypeEngraisMySuffix Management Component', () => {
        let comp: TypeEngraisMySuffixComponent;
        let fixture: ComponentFixture<TypeEngraisMySuffixComponent>;
        let service: TypeEngraisMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TypeEngraisMySuffixComponent],
                providers: []
            })
                .overrideTemplate(TypeEngraisMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TypeEngraisMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeEngraisMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TypeEngraisMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.typeEngrais[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
