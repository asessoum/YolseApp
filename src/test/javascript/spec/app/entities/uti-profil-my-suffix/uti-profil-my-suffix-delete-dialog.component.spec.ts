/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { UtiProfilMySuffixDeleteDialogComponent } from 'app/entities/uti-profil-my-suffix/uti-profil-my-suffix-delete-dialog.component';
import { UtiProfilMySuffixService } from 'app/entities/uti-profil-my-suffix/uti-profil-my-suffix.service';

describe('Component Tests', () => {
    describe('UtiProfilMySuffix Management Delete Component', () => {
        let comp: UtiProfilMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<UtiProfilMySuffixDeleteDialogComponent>;
        let service: UtiProfilMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [UtiProfilMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(UtiProfilMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtiProfilMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtiProfilMySuffixService);
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
