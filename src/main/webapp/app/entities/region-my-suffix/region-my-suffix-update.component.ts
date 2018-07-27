import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRegionMySuffix } from 'app/shared/model/region-my-suffix.model';
import { RegionMySuffixService } from './region-my-suffix.service';

@Component({
    selector: 'jhi-region-my-suffix-update',
    templateUrl: './region-my-suffix-update.component.html'
})
export class RegionMySuffixUpdateComponent implements OnInit {
    private _region: IRegionMySuffix;
    isSaving: boolean;
    creeLe: string;
    modifLe: string;

    constructor(private regionService: RegionMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ region }) => {
            this.region = region;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.region.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.region.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.region.id !== undefined) {
            this.subscribeToSaveResponse(this.regionService.update(this.region));
        } else {
            this.subscribeToSaveResponse(this.regionService.create(this.region));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRegionMySuffix>>) {
        result.subscribe((res: HttpResponse<IRegionMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get region() {
        return this._region;
    }

    set region(region: IRegionMySuffix) {
        this._region = region;
        this.creeLe = moment(region.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(region.modifLe).format(DATE_TIME_FORMAT);
    }
}
