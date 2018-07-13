/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { YolseAppTestModule } from '../../../test.module';
import { TypeEngraisMySuffixDeleteDialogComponent } from 'app/entities/type-engrais-my-suffix/type-engrais-my-suffix-delete-dialog.component';
import { TypeEngraisMySuffixService } from 'app/entities/type-engrais-my-suffix/type-engrais-my-suffix.service';

describe('Component Tests', () => {
    describe('TypeEngraisMySuffix Management Delete Component', () => {
        let comp: TypeEngraisMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TypeEngraisMySuffixDeleteDialogComponent>;
        let service: TypeEngraisMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [YolseAppTestModule],
                declarations: [TypeEngraisMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(TypeEngraisMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeEngraisMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeEngraisMySuffixService);
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
