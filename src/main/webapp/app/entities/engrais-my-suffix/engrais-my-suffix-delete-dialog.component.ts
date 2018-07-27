import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';
import { EngraisMySuffixService } from './engrais-my-suffix.service';

@Component({
    selector: 'jhi-engrais-my-suffix-delete-dialog',
    templateUrl: './engrais-my-suffix-delete-dialog.component.html'
})
export class EngraisMySuffixDeleteDialogComponent {
    engrais: IEngraisMySuffix;

    constructor(
        private engraisService: EngraisMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.engraisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'engraisListModification',
                content: 'Deleted an engrais'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-engrais-my-suffix-delete-popup',
    template: ''
})
export class EngraisMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ engrais }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EngraisMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.engrais = engrais;
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
