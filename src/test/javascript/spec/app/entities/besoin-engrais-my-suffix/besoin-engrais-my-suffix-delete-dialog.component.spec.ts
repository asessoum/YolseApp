/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { BesoinEngraisMySuffixDeleteDialogComponent } from 'app/entities/besoin-engrais-my-suffix/besoin-engrais-my-suffix-delete-dialog.component';
import { BesoinEngraisMySuffixService } from 'app/entities/besoin-engrais-my-suffix/besoin-engrais-my-suffix.service';

describe('Component Tests', () => {
    describe('BesoinEngraisMySuffix Management Delete Component', () => {
        let comp: BesoinEngraisMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<BesoinEngraisMySuffixDeleteDialogComponent>;
        let service: BesoinEngraisMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [BesoinEngraisMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(BesoinEngraisMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BesoinEngraisMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BesoinEngraisMySuffixService);
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
