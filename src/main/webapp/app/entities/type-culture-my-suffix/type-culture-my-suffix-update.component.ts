import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';
import { TypeCultureMySuffixService } from './type-culture-my-suffix.service';
import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';
import { BesoinIntrantMySuffixService } from 'app/entities/besoin-intrant-my-suffix';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from 'app/entities/utilisateur-my-suffix';

@Component({
    selector: 'jhi-type-culture-my-suffix-update',
    templateUrl: './type-culture-my-suffix-update.component.html'
})
export class TypeCultureMySuffixUpdateComponent implements OnInit {
    private _typeCulture: ITypeCultureMySuffix;
    isSaving: boolean;

    besoinintrants: IBesoinIntrantMySuffix[];

    utilisateurs: IUtilisateurMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private typeCultureService: TypeCultureMySuffixService,
        private besoinIntrantService: BesoinIntrantMySuffixService,
        private utilisateurService: UtilisateurMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeCulture }) => {
            this.typeCulture = typeCulture;
        });
        this.besoinIntrantService.query().subscribe(
            (res: HttpResponse<IBesoinIntrantMySuffix[]>) => {
                this.besoinintrants = res.body;
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
        this.typeCulture.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.typeCulture.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.typeCulture.id !== undefined) {
            this.subscribeToSaveResponse(this.typeCultureService.update(this.typeCulture));
        } else {
            this.subscribeToSaveResponse(this.typeCultureService.create(this.typeCulture));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITypeCultureMySuffix>>) {
        result.subscribe((res: HttpResponse<ITypeCultureMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }
    get typeCulture() {
        return this._typeCulture;
    }

    set typeCulture(typeCulture: ITypeCultureMySuffix) {
        this._typeCulture = typeCulture;
        this.creeLe = moment(typeCulture.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(typeCulture.modifLe).format(DATE_TIME_FORMAT);
    }
}
