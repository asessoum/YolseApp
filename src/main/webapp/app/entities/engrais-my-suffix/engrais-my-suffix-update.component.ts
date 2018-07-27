import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';
import { EngraisMySuffixService } from './engrais-my-suffix.service';

@Component({
    selector: 'jhi-engrais-my-suffix-update',
    templateUrl: './engrais-my-suffix-update.component.html'
})
export class EngraisMySuffixUpdateComponent implements OnInit {
    private _engrais: IEngraisMySuffix;
    isSaving: boolean;
    creeLe: string;
    modifLe: string;

    constructor(private engraisService: EngraisMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ engrais }) => {
            this.engrais = engrais;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.engrais.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.engrais.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.engrais.id !== undefined) {
            this.subscribeToSaveResponse(this.engraisService.update(this.engrais));
        } else {
            this.subscribeToSaveResponse(this.engraisService.create(this.engrais));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEngraisMySuffix>>) {
        result.subscribe((res: HttpResponse<IEngraisMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get engrais() {
        return this._engrais;
    }

    set engrais(engrais: IEngraisMySuffix) {
        this._engrais = engrais;
        this.creeLe = moment(engrais.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(engrais.modifLe).format(DATE_TIME_FORMAT);
    }
}
