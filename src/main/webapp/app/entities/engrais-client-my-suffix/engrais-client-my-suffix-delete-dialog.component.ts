import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';
import { EngraisClientMySuffixService } from './engrais-client-my-suffix.service';

@Component({
    selector: 'jhi-engrais-client-my-suffix-delete-dialog',
    templateUrl: './engrais-client-my-suffix-delete-dialog.component.html'
})
export class EngraisClientMySuffixDeleteDialogComponent {
    engraisClient: IEngraisClientMySuffix;

    constructor(
        private engraisClientService: EngraisClientMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.engraisClientService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'engraisClientListModification',
                content: 'Deleted an engraisClient'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-engrais-client-my-suffix-delete-popup',
    template: ''
})
export class EngraisClientMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ engraisClient }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EngraisClientMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.engraisClient = engraisClient;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
