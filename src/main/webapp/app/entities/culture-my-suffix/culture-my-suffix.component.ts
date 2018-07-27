import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICultureMySuffix } from 'app/shared/model/culture-my-suffix.model';
import { Principal } from 'app/core';
import { CultureMySuffixService } from './culture-my-suffix.service';

@Component({
    selector: 'jhi-culture-my-suffix',
    templateUrl: './culture-my-suffix.component.html'
})
export class CultureMySuffixComponent implements OnInit, OnDestroy {
    cultures: ICultureMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private cultureService: CultureMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.cultureService.query().subscribe(
            (res: HttpResponse<ICultureMySuffix[]>) => {
                this.cultures = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCultures();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICultureMySuffix) {
        return item.id;
    }

    registerChangeInCultures() {
        this.eventSubscriber = this.eventManager.subscribe('cultureListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
