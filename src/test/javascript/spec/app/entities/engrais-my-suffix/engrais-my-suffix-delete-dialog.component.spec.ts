/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { EngraisMySuffixDeleteDialogComponent } from 'app/entities/engrais-my-suffix/engrais-my-suffix-delete-dialog.component';
import { EngraisMySuffixService } from 'app/entities/engrais-my-suffix/engrais-my-suffix.service';

describe('Component Tests', () => {
    describe('EngraisMySuffix Management Delete Component', () => {
        let comp: EngraisMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<EngraisMySuffixDeleteDialogComponent>;
        let service: EngraisMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [EngraisMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(EngraisMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EngraisMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EngraisMySuffixService);
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
