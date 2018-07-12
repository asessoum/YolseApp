import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ILangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { Principal } from 'app/core';
import { LangueMySuffixService } from './langue-my-suffix.service';

@Component({
    selector: 'jhi-langue-my-suffix',
    templateUrl: './langue-my-suffix.component.html'
})
export class LangueMySuffixComponent implements OnInit, OnDestroy {
    langues: ILangueMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private langueService: LangueMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.langueService.query().subscribe(
            (res: HttpResponse<ILangueMySuffix[]>) => {
                this.langues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInLangues();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ILangueMySuffix) {
        return item.id;
    }

    registerChangeInLangues() {
        this.eventSubscriber = this.eventManager.subscribe('langueListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
