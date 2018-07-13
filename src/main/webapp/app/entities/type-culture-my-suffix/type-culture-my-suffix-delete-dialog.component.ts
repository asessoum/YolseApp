import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';
import { TypeCultureMySuffixService } from './type-culture-my-suffix.service';

@Component({
    selector: 'jhi-type-culture-my-suffix-delete-dialog',
    templateUrl: './type-culture-my-suffix-delete-dialog.component.html'
})
export class TypeCultureMySuffixDeleteDialogComponent {
    typeCulture: ITypeCultureMySuffix;

    constructor(
        private typeCultureService: TypeCultureMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeCultureService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeCultureListModification',
                content: 'Deleted an typeCulture'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-culture-my-suffix-delete-popup',
    template: ''
})
export class TypeCultureMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeCulture }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeCultureMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.typeCulture = typeCulture;
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
