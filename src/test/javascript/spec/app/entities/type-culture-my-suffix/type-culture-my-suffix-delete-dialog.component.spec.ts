/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { TypeCultureMySuffixDeleteDialogComponent } from 'app/entities/type-culture-my-suffix/type-culture-my-suffix-delete-dialog.component';
import { TypeCultureMySuffixService } from 'app/entities/type-culture-my-suffix/type-culture-my-suffix.service';

describe('Component Tests', () => {
    describe('TypeCultureMySuffix Management Delete Component', () => {
        let comp: TypeCultureMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TypeCultureMySuffixDeleteDialogComponent>;
        let service: TypeCultureMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TypeCultureMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(TypeCultureMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeCultureMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeCultureMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
