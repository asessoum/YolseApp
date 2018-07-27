import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { ProvinceMySuffixService } from './province-my-suffix.service';
import { IRegionMySuffix } from 'app/shared/model/region-my-suffix.model';
import { RegionMySuffixService } from 'app/entities/region-my-suffix';

@Component({
    selector: 'jhi-province-my-suffix-update',
    templateUrl: './province-my-suffix-update.component.html'
})
export class ProvinceMySuffixUpdateComponent implements OnInit {
    private _province: IProvinceMySuffix;
    isSaving: boolean;

    regions: IRegionMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private provinceService: ProvinceMySuffixService,
        private regionService: RegionMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ province }) => {
            this.province = province;
        });
        this.regionService.query().subscribe(
            (res: HttpResponse<IRegionMySuffix[]>) => {
                this.regions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.province.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.province.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.province.id !== undefined) {
            this.subscribeToSaveResponse(this.provinceService.update(this.province));
        } else {
            this.subscribeToSaveResponse(this.provinceService.create(this.province));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProvinceMySuffix>>) {
        result.subscribe((res: HttpResponse<IProvinceMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRegionById(index: number, item: IRegionMySuffix) {
        return item.id;
    }
    get province() {
        return this._province;
    }

    set province(province: IProvinceMySuffix) {
        this._province = province;
        this.creeLe = moment(province.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(province.modifLe).format(DATE_TIME_FORMAT);
    }
}
