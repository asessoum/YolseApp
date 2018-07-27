import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { LangueMySuffixService } from './langue-my-suffix.service';

@Component({
    selector: 'jhi-langue-my-suffix-update',
    templateUrl: './langue-my-suffix-update.component.html'
})
export class LangueMySuffixUpdateComponent implements OnInit {
    private _langue: ILangueMySuffix;
    isSaving: boolean;
    creeLe: string;
    modifLe: string;

    constructor(private langueService: LangueMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ langue }) => {
            this.langue = langue;
        });
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
    get langue() {
        return this._langue;
    }

    set langue(langue: ILangueMySuffix) {
        this._langue = langue;
        this.creeLe = moment(langue.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(langue.modifLe).format(DATE_TIME_FORMAT);
    }
}
