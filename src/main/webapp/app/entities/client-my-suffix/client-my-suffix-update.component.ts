import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';
import { ClientMySuffixService } from './client-my-suffix.service';
import { ILangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { LangueMySuffixService } from 'app/entities/langue-my-suffix';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from 'app/entities/utilisateur-my-suffix';
import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';
import { CommuneMySuffixService } from 'app/entities/commune-my-suffix';

@Component({
    selector: 'jhi-client-my-suffix-update',
    templateUrl: './client-my-suffix-update.component.html'
})
export class ClientMySuffixUpdateComponent implements OnInit {
    private _client: IClientMySuffix;
    isSaving: boolean;

    langues: ILangueMySuffix[];

    utilisateurs: IUtilisateurMySuffix[];

    communes: ICommuneMySuffix[];
    naissance: string;
    dCarteUtil: string;
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private clientService: ClientMySuffixService,
        private langueService: LangueMySuffixService,
        private utilisateurService: UtilisateurMySuffixService,
        private communeService: CommuneMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ client }) => {
            this.client = client;
        });
        this.langueService.query().subscribe(
            (res: HttpResponse<ILangueMySuffix[]>) => {
                this.langues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateurMySuffix[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.communeService.query().subscribe(
            (res: HttpResponse<ICommuneMySuffix[]>) => {
                this.communes = res.body;
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

    trackLangueById(index: number, item: ILangueMySuffix) {
        return item.id;
    }

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }

    trackCommuneById(index: number, item: ICommuneMySuffix) {
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
