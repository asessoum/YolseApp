import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReferenceMySuffix } from 'app/shared/model/reference-my-suffix.model';
import { ReferenceMySuffixService } from './reference-my-suffix.service';

@Component({
    selector: 'jhi-reference-my-suffix-update',
    templateUrl: './reference-my-suffix-update.component.html'
})
export class ReferenceMySuffixUpdateComponent implements OnInit {
    private _reference: IReferenceMySuffix;
    isSaving: boolean;
    creeLe: string;
    modifLe: string;

    constructor(private referenceService: ReferenceMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ reference }) => {
            this.reference = reference;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.reference.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.reference.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.reference.id !== undefined) {
            this.subscribeToSaveResponse(this.referenceService.update(this.reference));
        } else {
            this.subscribeToSaveResponse(this.referenceService.create(this.reference));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReferenceMySuffix>>) {
        result.subscribe((res: HttpResponse<IReferenceMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get reference() {
        return this._reference;
    }

    set reference(reference: IReferenceMySuffix) {
        this._reference = reference;
        this.creeLe = moment(reference.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(reference.modifLe).format(DATE_TIME_FORMAT);
    }
}
