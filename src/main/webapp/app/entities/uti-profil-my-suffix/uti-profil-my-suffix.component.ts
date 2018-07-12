import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';
import { Principal } from 'app/core';
import { UtiProfilMySuffixService } from './uti-profil-my-suffix.service';

@Component({
    selector: 'jhi-uti-profil-my-suffix',
    templateUrl: './uti-profil-my-suffix.component.html'
})
export class UtiProfilMySuffixComponent implements OnInit, OnDestroy {
    utiProfils: IUtiProfilMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private utiProfilService: UtiProfilMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.utiProfilService.query().subscribe(
            (res: HttpResponse<IUtiProfilMySuffix[]>) => {
                this.utiProfils = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInUtiProfils();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IUtiProfilMySuffix) {
        return item.id;
    }

    registerChangeInUtiProfils() {
        this.eventSubscriber = this.eventManager.subscribe('utiProfilListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
