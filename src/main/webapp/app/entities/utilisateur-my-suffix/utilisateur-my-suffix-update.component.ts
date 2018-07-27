import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from './utilisateur-my-suffix.service';
import { IUtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';
import { UtiProfileMySuffixService } from 'app/entities/uti-profile-my-suffix';
import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';
import { ClientMySuffixService } from 'app/entities/client-my-suffix';
import { ISuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';
import { SuiviChampsMySuffixService } from 'app/entities/suivi-champs-my-suffix';

@Component({
    selector: 'jhi-utilisateur-my-suffix-update',
    templateUrl: './utilisateur-my-suffix-update.component.html'
})
export class UtilisateurMySuffixUpdateComponent implements OnInit {
    private _utilisateur: IUtilisateurMySuffix;
    isSaving: boolean;

    utilisateurs: IUtilisateurMySuffix[];

    utiprofiles: IUtiProfileMySuffix[];

    clients: IClientMySuffix[];

    suivichamps: ISuiviChampsMySuffix[];
    dateNaiss: string;
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private utilisateurService: UtilisateurMySuffixService,
        private utiProfileService: UtiProfileMySuffixService,
        private clientService: ClientMySuffixService,
        private suiviChampsService: SuiviChampsMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utilisateur }) => {
            this.utilisateur = utilisateur;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateurMySuffix[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.utiProfileService.query().subscribe(
            (res: HttpResponse<IUtiProfileMySuffix[]>) => {
                this.utiprofiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.clientService.query().subscribe(
            (res: HttpResponse<IClientMySuffix[]>) => {
                this.clients = res.body;
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
        this.utilisateur.dateNaiss = moment(this.dateNaiss, DATE_TIME_FORMAT);
        this.utilisateur.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.utilisateur.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.utilisateur.id !== undefined) {
            this.subscribeToSaveResponse(this.utilisateurService.update(this.utilisateur));
        } else {
            this.subscribeToSaveResponse(this.utilisateurService.create(this.utilisateur));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUtilisateurMySuffix>>) {
        result.subscribe((res: HttpResponse<IUtilisateurMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }

    trackUtiProfileById(index: number, item: IUtiProfileMySuffix) {
        return item.id;
    }

    trackClientById(index: number, item: IClientMySuffix) {
        return item.id;
    }

    trackSuiviChampsById(index: number, item: ISuiviChampsMySuffix) {
        return item.id;
    }
    get utilisateur() {
        return this._utilisateur;
    }

    set utilisateur(utilisateur: IUtilisateurMySuffix) {
        this._utilisateur = utilisateur;
        this.dateNaiss = moment(utilisateur.dateNaiss).format(DATE_TIME_FORMAT);
        this.creeLe = moment(utilisateur.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(utilisateur.modifLe).format(DATE_TIME_FORMAT);
    }
}
