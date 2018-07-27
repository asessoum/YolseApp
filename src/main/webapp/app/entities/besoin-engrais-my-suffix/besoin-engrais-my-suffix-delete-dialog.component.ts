import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBesoinEngraisMySuffix } from 'app/shared/model/besoin-engrais-my-suffix.model';
import { BesoinEngraisMySuffixService } from './besoin-engrais-my-suffix.service';

@Component({
    selector: 'jhi-besoin-engrais-my-suffix-delete-dialog',
    templateUrl: './besoin-engrais-my-suffix-delete-dialog.component.html'
})
export class BesoinEngraisMySuffixDeleteDialogComponent {
    besoinEngrais: IBesoinEngraisMySuffix;

    constructor(
        private besoinEngraisService: BesoinEngraisMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.besoinEngraisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'besoinEngraisListModification',
                content: 'Deleted an besoinEngrais'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-besoin-engrais-my-suffix-delete-popup',
    template: ''
})
export class BesoinEngraisMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ besoinEngrais }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BesoinEngraisMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.besoinEngrais = besoinEngrais;
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
