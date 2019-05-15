/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { TransactionMySuffixDeleteDialogComponent } from 'app/entities/transaction-my-suffix/transaction-my-suffix-delete-dialog.component';
import { TransactionMySuffixService } from 'app/entities/transaction-my-suffix/transaction-my-suffix.service';

describe('Component Tests', () => {
    describe('TransactionMySuffix Management Delete Component', () => {
        let comp: TransactionMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TransactionMySuffixDeleteDialogComponent>;
        let service: TransactionMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TransactionMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(TransactionMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TransactionMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransactionMySuffixService);
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
