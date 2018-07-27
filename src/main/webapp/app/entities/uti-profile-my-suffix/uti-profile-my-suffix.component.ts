import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';
import { Principal } from 'app/core';
import { UtiProfileMySuffixService } from './uti-profile-my-suffix.service';

@Component({
    selector: 'jhi-uti-profile-my-suffix',
    templateUrl: './uti-profile-my-suffix.component.html'
})
export class UtiProfileMySuffixComponent implements OnInit, OnDestroy {
    utiProfiles: IUtiProfileMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private utiProfileService: UtiProfileMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.utiProfileService.query().subscribe(
            (res: HttpResponse<IUtiProfileMySuffix[]>) => {
                this.utiProfiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInUtiProfiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IUtiProfileMySuffix) {
        return item.id;
    }

    registerChangeInUtiProfiles() {
        this.eventSubscriber = this.eventManager.subscribe('utiProfileListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
