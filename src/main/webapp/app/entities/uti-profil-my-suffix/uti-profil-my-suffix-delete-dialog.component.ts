import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';
import { UtiProfilMySuffixService } from './uti-profil-my-suffix.service';

@Component({
    selector: 'jhi-uti-profil-my-suffix-delete-dialog',
    templateUrl: './uti-profil-my-suffix-delete-dialog.component.html'
})
export class UtiProfilMySuffixDeleteDialogComponent {
    utiProfil: IUtiProfilMySuffix;

    constructor(
        private utiProfilService: UtiProfilMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.utiProfilService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'utiProfilListModification',
                content: 'Deleted an utiProfil'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-uti-profil-my-suffix-delete-popup',
    template: ''
})
export class UtiProfilMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utiProfil }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UtiProfilMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.utiProfil = utiProfil;
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
