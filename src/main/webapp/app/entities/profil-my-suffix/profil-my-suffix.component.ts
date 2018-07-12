import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';
import { Principal } from 'app/core';
import { ProfilMySuffixService } from './profil-my-suffix.service';

@Component({
    selector: 'jhi-profil-my-suffix',
    templateUrl: './profil-my-suffix.component.html'
})
export class ProfilMySuffixComponent implements OnInit, OnDestroy {
    profils: IProfilMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private profilService: ProfilMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.profilService.query().subscribe(
            (res: HttpResponse<IProfilMySuffix[]>) => {
                this.profils = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProfils();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProfilMySuffix) {
        return item.id;
    }

    registerChangeInProfils() {
        this.eventSubscriber = this.eventManager.subscribe('profilListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
