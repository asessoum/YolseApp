import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICultureMySuffix } from 'app/shared/model/culture-my-suffix.model';
import { CultureMySuffixService } from './culture-my-suffix.service';

@Component({
    selector: 'jhi-culture-my-suffix-delete-dialog',
    templateUrl: './culture-my-suffix-delete-dialog.component.html'
})
export class CultureMySuffixDeleteDialogComponent {
    culture: ICultureMySuffix;

    constructor(
        private cultureService: CultureMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cultureService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cultureListModification',
                content: 'Deleted an culture'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-culture-my-suffix-delete-popup',
    template: ''
})
export class CultureMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ culture }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CultureMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.culture = culture;
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
