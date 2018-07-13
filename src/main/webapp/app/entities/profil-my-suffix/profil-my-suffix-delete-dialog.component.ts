import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';
import { ProfilMySuffixService } from './profil-my-suffix.service';

@Component({
    selector: 'jhi-profil-my-suffix-delete-dialog',
    templateUrl: './profil-my-suffix-delete-dialog.component.html'
})
export class ProfilMySuffixDeleteDialogComponent {
    profil: IProfilMySuffix;

    constructor(private profilService: ProfilMySuffixService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.profilService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'profilListModification',
                content: 'Deleted an profil'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-profil-my-suffix-delete-popup',
    template: ''
})
export class ProfilMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profil }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProfilMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.profil = profil;
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
