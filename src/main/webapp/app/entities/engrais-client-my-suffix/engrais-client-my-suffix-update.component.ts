import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';
import { EngraisClientMySuffixService } from './engrais-client-my-suffix.service';
import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';
import { BesoinIntrantMySuffixService } from 'app/entities/besoin-intrant-my-suffix';

@Component({
    selector: 'jhi-engrais-client-my-suffix-update',
    templateUrl: './engrais-client-my-suffix-update.component.html'
})
export class EngraisClientMySuffixUpdateComponent implements OnInit {
    private _engraisClient: IEngraisClientMySuffix;
    isSaving: boolean;

    besoinintrants: IBesoinIntrantMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private engraisClientService: EngraisClientMySuffixService,
        private besoinIntrantService: BesoinIntrantMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ engraisClient }) => {
            this.engraisClient = engraisClient;
        });
        this.besoinIntrantService.query().subscribe(
            (res: HttpResponse<IBesoinIntrantMySuffix[]>) => {
                this.besoinintrants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.engraisClient.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.engraisClient.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.engraisClient.id !== undefined) {
            this.subscribeToSaveResponse(this.engraisClientService.update(this.engraisClient));
        } else {
            this.subscribeToSaveResponse(this.engraisClientService.create(this.engraisClient));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEngraisClientMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEngraisClientMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackBesoinIntrantById(index: number, item: IBesoinIntrantMySuffix) {
        return item.id;
    }
    get engraisClient() {
        return this._engraisClient;
    }

    set engraisClient(engraisClient: IEngraisClientMySuffix) {
        this._engraisClient = engraisClient;
        this.creeLe = moment(engraisClient.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(engraisClient.modifLe).format(DATE_TIME_FORMAT);
    }
}
