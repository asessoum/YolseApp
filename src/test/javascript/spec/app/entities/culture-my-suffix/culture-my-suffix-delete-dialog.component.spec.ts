/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { CultureMySuffixDeleteDialogComponent } from 'app/entities/culture-my-suffix/culture-my-suffix-delete-dialog.component';
import { CultureMySuffixService } from 'app/entities/culture-my-suffix/culture-my-suffix.service';

describe('Component Tests', () => {
    describe('CultureMySuffix Management Delete Component', () => {
        let comp: CultureMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<CultureMySuffixDeleteDialogComponent>;
        let service: CultureMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [CultureMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(CultureMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CultureMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CultureMySuffixService);
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
