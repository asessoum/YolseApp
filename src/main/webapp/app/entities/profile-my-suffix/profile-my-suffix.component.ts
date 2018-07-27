import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { Principal } from 'app/core';
import { ProfileMySuffixService } from './profile-my-suffix.service';

@Component({
    selector: 'jhi-profile-my-suffix',
    templateUrl: './profile-my-suffix.component.html'
})
export class ProfileMySuffixComponent implements OnInit, OnDestroy {
    profiles: IProfileMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private profileService: ProfileMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.profileService.query().subscribe(
            (res: HttpResponse<IProfileMySuffix[]>) => {
                this.profiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProfiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProfileMySuffix) {
        return item.id;
    }

    registerChangeInProfiles() {
        this.eventSubscriber = this.eventManager.subscribe('profileListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
