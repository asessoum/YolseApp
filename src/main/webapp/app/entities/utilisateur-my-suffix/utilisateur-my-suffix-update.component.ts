import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from './utilisateur-my-suffix.service';
import { ILangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { LangueMySuffixService } from 'app/entities/langue-my-suffix';
import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';
import { CommuneMySuffixService } from 'app/entities/commune-my-suffix';

@Component({
    selector: 'jhi-utilisateur-my-suffix-update',
    templateUrl: './utilisateur-my-suffix-update.component.html'
})
export class UtilisateurMySuffixUpdateComponent implements OnInit {
    private _utilisateur: IUtilisateurMySuffix;
    isSaving: boolean;

    langues: ILangueMySuffix[];

    communes: ICommuneMySuffix[];

    utilisateurs: IUtilisateurMySuffix[];
    dateNaiss: string;
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private utilisateurService: UtilisateurMySuffixService,
        private langueService: LangueMySuffixService,
        private communeService: CommuneMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utilisateur }) => {
            this.utilisateur = utilisateur;
        });
        this.langueService.query().subscribe(
            (res: HttpResponse<ILangueMySuffix[]>) => {
                this.langues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.communeService.query().subscribe(
            (res: HttpResponse<ICommuneMySuffix[]>) => {
                this.communes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateurMySuffix[]>) => {
                this.utilisateurs = res.body;
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

    trackLangueById(index: number, item: ILangueMySuffix) {
        return item.id;
    }

    trackCommuneById(index: number, item: ICommuneMySuffix) {
        return item.id;
    }

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
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
