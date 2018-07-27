import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';
import { SuiviChampsMySuffixService } from './suivi-champs-my-suffix.service';
import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';
import { ClientMySuffixService } from 'app/entities/client-my-suffix';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from 'app/entities/utilisateur-my-suffix';
import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';
import { CommuneMySuffixService } from 'app/entities/commune-my-suffix';

@Component({
    selector: 'jhi-suivi-champs-my-suffix-update',
    templateUrl: './suivi-champs-my-suffix-update.component.html'
})
export class SuiviChampsMySuffixUpdateComponent implements OnInit {
    private _suiviChamps: ISuiviChampsMySuffix;
    isSaving: boolean;

    clients: IClientMySuffix[];

    utilisateurs: IUtilisateurMySuffix[];

    communes: ICommuneMySuffix[];
    dVisit: string;
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private suiviChampsService: SuiviChampsMySuffixService,
        private clientService: ClientMySuffixService,
        private utilisateurService: UtilisateurMySuffixService,
        private communeService: CommuneMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ suiviChamps }) => {
            this.suiviChamps = suiviChamps;
        });
        this.clientService.query().subscribe(
            (res: HttpResponse<IClientMySuffix[]>) => {
                this.clients = res.body;
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
        this.suiviChamps.dVisit = moment(this.dVisit, DATE_TIME_FORMAT);
        this.suiviChamps.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.suiviChamps.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.suiviChamps.id !== undefined) {
            this.subscribeToSaveResponse(this.suiviChampsService.update(this.suiviChamps));
        } else {
            this.subscribeToSaveResponse(this.suiviChampsService.create(this.suiviChamps));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISuiviChampsMySuffix>>) {
        result.subscribe((res: HttpResponse<ISuiviChampsMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackClientById(index: number, item: IClientMySuffix) {
        return item.id;
    }

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }

    trackCommuneById(index: number, item: ICommuneMySuffix) {
        return item.id;
    }
    get suiviChamps() {
        return this._suiviChamps;
    }

    set suiviChamps(suiviChamps: ISuiviChampsMySuffix) {
        this._suiviChamps = suiviChamps;
        this.dVisit = moment(suiviChamps.dVisit).format(DATE_TIME_FORMAT);
        this.creeLe = moment(suiviChamps.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(suiviChamps.modifLe).format(DATE_TIME_FORMAT);
    }
}
