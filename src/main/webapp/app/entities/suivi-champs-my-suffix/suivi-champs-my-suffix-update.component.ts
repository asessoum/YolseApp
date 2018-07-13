import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';
import { SuiviChampsMySuffixService } from './suivi-champs-my-suffix.service';

@Component({
    selector: 'jhi-suivi-champs-my-suffix-update',
    templateUrl: './suivi-champs-my-suffix-update.component.html'
})
export class SuiviChampsMySuffixUpdateComponent implements OnInit {
    private _suiviChamps: ISuiviChampsMySuffix;
    isSaving: boolean;
    dVisit: string;
    creeLe: string;
    modifLe: string;

    constructor(private suiviChampsService: SuiviChampsMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ suiviChamps }) => {
            this.suiviChamps = suiviChamps;
        });
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
