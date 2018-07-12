import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';
import { Principal } from 'app/core';
import { CommuneMySuffixService } from './commune-my-suffix.service';

@Component({
    selector: 'jhi-commune-my-suffix',
    templateUrl: './commune-my-suffix.component.html'
})
export class CommuneMySuffixComponent implements OnInit, OnDestroy {
    communes: ICommuneMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private communeService: CommuneMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.communeService.query().subscribe(
            (res: HttpResponse<ICommuneMySuffix[]>) => {
                this.communes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCommunes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICommuneMySuffix) {
        return item.id;
    }

    registerChangeInCommunes() {
        this.eventSubscriber = this.eventManager.subscribe('communeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
