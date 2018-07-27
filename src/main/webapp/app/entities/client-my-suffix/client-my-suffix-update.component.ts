import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';
import { ClientMySuffixService } from './client-my-suffix.service';
import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';
import { BesoinIntrantMySuffixService } from 'app/entities/besoin-intrant-my-suffix';
import { ISuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';
import { SuiviChampsMySuffixService } from 'app/entities/suivi-champs-my-suffix';

@Component({
    selector: 'jhi-client-my-suffix-update',
    templateUrl: './client-my-suffix-update.component.html'
})
export class ClientMySuffixUpdateComponent implements OnInit {
    private _client: IClientMySuffix;
    isSaving: boolean;

    besoinintrants: IBesoinIntrantMySuffix[];

    suivichamps: ISuiviChampsMySuffix[];
    naissance: string;
    dCarteUtil: string;
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private clientService: ClientMySuffixService,
        private besoinIntrantService: BesoinIntrantMySuffixService,
        private suiviChampsService: SuiviChampsMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ client }) => {
            this.client = client;
        });
        this.besoinIntrantService.query().subscribe(
            (res: HttpResponse<IBesoinIntrantMySuffix[]>) => {
                this.besoinintrants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.suiviChampsService.query().subscribe(
            (res: HttpResponse<ISuiviChampsMySuffix[]>) => {
                this.suivichamps = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.client.naissance = moment(this.naissance, DATE_TIME_FORMAT);
        this.client.dCarteUtil = moment(this.dCarteUtil, DATE_TIME_FORMAT);
        this.client.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.client.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.client.id !== undefined) {
            this.subscribeToSaveResponse(this.clientService.update(this.client));
        } else {
            this.subscribeToSaveResponse(this.clientService.create(this.client));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IClientMySuffix>>) {
        result.subscribe((res: HttpResponse<IClientMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSuiviChampsById(index: number, item: ISuiviChampsMySuffix) {
        return item.id;
    }
    get client() {
        return this._client;
    }

    set client(client: IClientMySuffix) {
        this._client = client;
        this.naissance = moment(client.naissance).format(DATE_TIME_FORMAT);
        this.dCarteUtil = moment(client.dCarteUtil).format(DATE_TIME_FORMAT);
        this.creeLe = moment(client.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(client.modifLe).format(DATE_TIME_FORMAT);
    }
}
