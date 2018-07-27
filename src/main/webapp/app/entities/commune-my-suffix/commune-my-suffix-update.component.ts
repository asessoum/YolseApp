import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';
import { CommuneMySuffixService } from './commune-my-suffix.service';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { ProvinceMySuffixService } from 'app/entities/province-my-suffix';

@Component({
    selector: 'jhi-commune-my-suffix-update',
    templateUrl: './commune-my-suffix-update.component.html'
})
export class CommuneMySuffixUpdateComponent implements OnInit {
    private _commune: ICommuneMySuffix;
    isSaving: boolean;

    provinces: IProvinceMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private communeService: CommuneMySuffixService,
        private provinceService: ProvinceMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ commune }) => {
            this.commune = commune;
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
        this.commune.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.commune.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.commune.id !== undefined) {
            this.subscribeToSaveResponse(this.communeService.update(this.commune));
        } else {
            this.subscribeToSaveResponse(this.communeService.create(this.commune));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICommuneMySuffix>>) {
        result.subscribe((res: HttpResponse<ICommuneMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get commune() {
        return this._commune;
    }

    set commune(commune: ICommuneMySuffix) {
        this._commune = commune;
        this.creeLe = moment(commune.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(commune.modifLe).format(DATE_TIME_FORMAT);
    }
}
