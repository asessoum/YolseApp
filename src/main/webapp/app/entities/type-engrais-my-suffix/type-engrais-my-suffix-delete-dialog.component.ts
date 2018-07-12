import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';
import { TypeEngraisMySuffixService } from './type-engrais-my-suffix.service';

@Component({
    selector: 'jhi-type-engrais-my-suffix-delete-dialog',
    templateUrl: './type-engrais-my-suffix-delete-dialog.component.html'
})
export class TypeEngraisMySuffixDeleteDialogComponent {
    typeEngrais: ITypeEngraisMySuffix;

    constructor(
        private typeEngraisService: TypeEngraisMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeEngraisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeEngraisListModification',
                content: 'Deleted an typeEngrais'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-engrais-my-suffix-delete-popup',
    template: ''
})
export class TypeEngraisMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeEngrais }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeEngraisMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.typeEngrais = typeEngrais;
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
