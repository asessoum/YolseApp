import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRegionMySuffix } from 'app/shared/model/region-my-suffix.model';
import { RegionMySuffixService } from './region-my-suffix.service';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { ProvinceMySuffixService } from 'app/entities/province-my-suffix';

@Component({
    selector: 'jhi-region-my-suffix-update',
    templateUrl: './region-my-suffix-update.component.html'
})
export class RegionMySuffixUpdateComponent implements OnInit {
    private _region: IRegionMySuffix;
    isSaving: boolean;

    provinces: IProvinceMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private regionService: RegionMySuffixService,
        private provinceService: ProvinceMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ region }) => {
            this.region = region;
        });
        this.provinceService.query().subscribe(
            (res: HttpResponse<IProvinceMySuffix[]>) => {
                this.provinces = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProvinceById(index: number, item: IProvinceMySuffix) {
        return item.id;
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
