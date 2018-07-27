import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ILangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { LangueMySuffixService } from './langue-my-suffix.service';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from 'app/entities/utilisateur-my-suffix';
import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';
import { ClientMySuffixService } from 'app/entities/client-my-suffix';

@Component({
    selector: 'jhi-langue-my-suffix-update',
    templateUrl: './langue-my-suffix-update.component.html'
})
export class LangueMySuffixUpdateComponent implements OnInit {
    private _langue: ILangueMySuffix;
    isSaving: boolean;

    utilisateurs: IUtilisateurMySuffix[];

    clients: IClientMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private langueService: LangueMySuffixService,
        private utilisateurService: UtilisateurMySuffixService,
        private clientService: ClientMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ langue }) => {
            this.langue = langue;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateurMySuffix[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.clientService.query().subscribe(
            (res: HttpResponse<IClientMySuffix[]>) => {
                this.clients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.langue.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.langue.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.langue.id !== undefined) {
            this.subscribeToSaveResponse(this.langueService.update(this.langue));
        } else {
            this.subscribeToSaveResponse(this.langueService.create(this.langue));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILangueMySuffix>>) {
        result.subscribe((res: HttpResponse<ILangueMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackClientById(index: number, item: IClientMySuffix) {
        return item.id;
    }
    get langue() {
        return this._langue;
    }

    set langue(langue: ILangueMySuffix) {
        this._langue = langue;
        this.creeLe = moment(langue.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(langue.modifLe).format(DATE_TIME_FORMAT);
    }
}
