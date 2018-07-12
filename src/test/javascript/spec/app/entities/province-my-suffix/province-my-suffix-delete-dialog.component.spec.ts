/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { ProvinceMySuffixDeleteDialogComponent } from 'app/entities/province-my-suffix/province-my-suffix-delete-dialog.component';
import { ProvinceMySuffixService } from 'app/entities/province-my-suffix/province-my-suffix.service';

describe('Component Tests', () => {
    describe('ProvinceMySuffix Management Delete Component', () => {
        let comp: ProvinceMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ProvinceMySuffixDeleteDialogComponent>;
        let service: ProvinceMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [ProvinceMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(ProvinceMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProvinceMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvinceMySuffixService);
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
