import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';
import { Principal } from 'app/core';
import { EngraisMySuffixService } from './engrais-my-suffix.service';

@Component({
    selector: 'jhi-engrais-my-suffix',
    templateUrl: './engrais-my-suffix.component.html'
})
export class EngraisMySuffixComponent implements OnInit, OnDestroy {
    engrais: IEngraisMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private engraisService: EngraisMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.engraisService.query().subscribe(
            (res: HttpResponse<IEngraisMySuffix[]>) => {
                this.engrais = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEngrais();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEngraisMySuffix) {
        return item.id;
    }

    registerChangeInEngrais() {
        this.eventSubscriber = this.eventManager.subscribe('engraisListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
