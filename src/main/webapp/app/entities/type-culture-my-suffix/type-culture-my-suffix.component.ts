import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';
import { Principal } from 'app/core';
import { TypeCultureMySuffixService } from './type-culture-my-suffix.service';

@Component({
    selector: 'jhi-type-culture-my-suffix',
    templateUrl: './type-culture-my-suffix.component.html'
})
export class TypeCultureMySuffixComponent implements OnInit, OnDestroy {
    typeCultures: ITypeCultureMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private typeCultureService: TypeCultureMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.typeCultureService.query().subscribe(
            (res: HttpResponse<ITypeCultureMySuffix[]>) => {
                this.typeCultures = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeCultures();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITypeCultureMySuffix) {
        return item.id;
    }

    registerChangeInTypeCultures() {
        this.eventSubscriber = this.eventManager.subscribe('typeCultureListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
