import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';
import { Principal } from 'app/core';
import { TypeEngraisMySuffixService } from './type-engrais-my-suffix.service';

@Component({
    selector: 'jhi-type-engrais-my-suffix',
    templateUrl: './type-engrais-my-suffix.component.html'
})
export class TypeEngraisMySuffixComponent implements OnInit, OnDestroy {
    typeEngrais: ITypeEngraisMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private typeEngraisService: TypeEngraisMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.typeEngraisService.query().subscribe(
            (res: HttpResponse<ITypeEngraisMySuffix[]>) => {
                this.typeEngrais = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeEngrais();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITypeEngraisMySuffix) {
        return item.id;
    }

    registerChangeInTypeEngrais() {
        this.eventSubscriber = this.eventManager.subscribe('typeEngraisListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
