/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { SuiviChampsMySuffixDeleteDialogComponent } from 'app/entities/suivi-champs-my-suffix/suivi-champs-my-suffix-delete-dialog.component';
import { SuiviChampsMySuffixService } from 'app/entities/suivi-champs-my-suffix/suivi-champs-my-suffix.service';

describe('Component Tests', () => {
    describe('SuiviChampsMySuffix Management Delete Component', () => {
        let comp: SuiviChampsMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<SuiviChampsMySuffixDeleteDialogComponent>;
        let service: SuiviChampsMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [SuiviChampsMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(SuiviChampsMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SuiviChampsMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuiviChampsMySuffixService);
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
