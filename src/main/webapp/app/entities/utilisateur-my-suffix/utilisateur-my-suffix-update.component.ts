import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from './utilisateur-my-suffix.service';
import { IUtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';
import { UtiProfilMySuffixService } from 'app/entities/uti-profil-my-suffix';

@Component({
    selector: 'jhi-utilisateur-my-suffix-update',
    templateUrl: './utilisateur-my-suffix-update.component.html'
})
export class UtilisateurMySuffixUpdateComponent implements OnInit {
    private _utilisateur: IUtilisateurMySuffix;
    isSaving: boolean;

    utiprofils: IUtiProfilMySuffix[];
    naissance: string;
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private utilisateurService: UtilisateurMySuffixService,
        private utiProfilService: UtiProfilMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utilisateur }) => {
            this.utilisateur = utilisateur;
        });
        this.utiProfilService.query().subscribe(
            (res: HttpResponse<IUtiProfilMySuffix[]>) => {
                this.utiprofils = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.utilisateur.naissance = moment(this.naissance, DATE_TIME_FORMAT);
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

    trackUtiProfilById(index: number, item: IUtiProfilMySuffix) {
        return item.id;
    }
    get utilisateur() {
        return this._utilisateur;
    }

    set utilisateur(utilisateur: IUtilisateurMySuffix) {
        this._utilisateur = utilisateur;
        this.naissance = moment(utilisateur.naissance).format(DATE_TIME_FORMAT);
        this.creeLe = moment(utilisateur.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(utilisateur.modifLe).format(DATE_TIME_FORMAT);
    }
}
