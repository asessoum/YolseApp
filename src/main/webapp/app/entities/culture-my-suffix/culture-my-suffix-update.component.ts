import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ICultureMySuffix } from 'app/shared/model/culture-my-suffix.model';
import { CultureMySuffixService } from './culture-my-suffix.service';
import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';
import { BesoinIntrantMySuffixService } from 'app/entities/besoin-intrant-my-suffix';

@Component({
    selector: 'jhi-culture-my-suffix-update',
    templateUrl: './culture-my-suffix-update.component.html'
})
export class CultureMySuffixUpdateComponent implements OnInit {
    private _culture: ICultureMySuffix;
    isSaving: boolean;

    besoinintrants: IBesoinIntrantMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private cultureService: CultureMySuffixService,
        private besoinIntrantService: BesoinIntrantMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ culture }) => {
            this.culture = culture;
        });
        this.besoinIntrantService.query().subscribe(
            (res: HttpResponse<IBesoinIntrantMySuffix[]>) => {
                this.besoinintrants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.culture.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.culture.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.culture.id !== undefined) {
            this.subscribeToSaveResponse(this.cultureService.update(this.culture));
        } else {
            this.subscribeToSaveResponse(this.cultureService.create(this.culture));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICultureMySuffix>>) {
        result.subscribe((res: HttpResponse<ICultureMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get culture() {
        return this._culture;
    }

    set culture(culture: ICultureMySuffix) {
        this._culture = culture;
        this.creeLe = moment(culture.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(culture.modifLe).format(DATE_TIME_FORMAT);
    }
}
