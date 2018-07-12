import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';
import { SuiviChampsMySuffixService } from './suivi-champs-my-suffix.service';

@Component({
    selector: 'jhi-suivi-champs-my-suffix-delete-dialog',
    templateUrl: './suivi-champs-my-suffix-delete-dialog.component.html'
})
export class SuiviChampsMySuffixDeleteDialogComponent {
    suiviChamps: ISuiviChampsMySuffix;

    constructor(
        private suiviChampsService: SuiviChampsMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.suiviChampsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'suiviChampsListModification',
                content: 'Deleted an suiviChamps'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-suivi-champs-my-suffix-delete-popup',
    template: ''
})
export class SuiviChampsMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ suiviChamps }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SuiviChampsMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.suiviChamps = suiviChamps;
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
