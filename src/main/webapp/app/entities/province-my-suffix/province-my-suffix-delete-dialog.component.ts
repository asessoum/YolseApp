import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { ProvinceMySuffixService } from './province-my-suffix.service';

@Component({
    selector: 'jhi-province-my-suffix-delete-dialog',
    templateUrl: './province-my-suffix-delete-dialog.component.html'
})
export class ProvinceMySuffixDeleteDialogComponent {
    province: IProvinceMySuffix;

    constructor(
        private provinceService: ProvinceMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.provinceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'provinceListModification',
                content: 'Deleted an province'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-province-my-suffix-delete-popup',
    template: ''
})
export class ProvinceMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ province }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvinceMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.province = province;
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
