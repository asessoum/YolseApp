import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';
import { BesoinIntrantMySuffixService } from './besoin-intrant-my-suffix.service';

@Component({
    selector: 'jhi-besoin-intrant-my-suffix-update',
    templateUrl: './besoin-intrant-my-suffix-update.component.html'
})
export class BesoinIntrantMySuffixUpdateComponent implements OnInit {
    private _besoinIntrant: IBesoinIntrantMySuffix;
    isSaving: boolean;
    creeLe: string;
    modifLe: string;

    constructor(private besoinIntrantService: BesoinIntrantMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ besoinIntrant }) => {
            this.besoinIntrant = besoinIntrant;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.besoinIntrant.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.besoinIntrant.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.besoinIntrant.id !== undefined) {
            this.subscribeToSaveResponse(this.besoinIntrantService.update(this.besoinIntrant));
        } else {
            this.subscribeToSaveResponse(this.besoinIntrantService.create(this.besoinIntrant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBesoinIntrantMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IBesoinIntrantMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get besoinIntrant() {
        return this._besoinIntrant;
    }

    set besoinIntrant(besoinIntrant: IBesoinIntrantMySuffix) {
        this._besoinIntrant = besoinIntrant;
        this.creeLe = moment(besoinIntrant.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(besoinIntrant.modifLe).format(DATE_TIME_FORMAT);
    }
}
