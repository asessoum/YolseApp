import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';
import { UtiProfileMySuffixService } from './uti-profile-my-suffix.service';

@Component({
    selector: 'jhi-uti-profile-my-suffix-update',
    templateUrl: './uti-profile-my-suffix-update.component.html'
})
export class UtiProfileMySuffixUpdateComponent implements OnInit {
    private _utiProfile: IUtiProfileMySuffix;
    isSaving: boolean;
    creeLe: string;
    modifLe: string;

    constructor(private utiProfileService: UtiProfileMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utiProfile }) => {
            this.utiProfile = utiProfile;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.utiProfile.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.utiProfile.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.utiProfile.id !== undefined) {
            this.subscribeToSaveResponse(this.utiProfileService.update(this.utiProfile));
        } else {
            this.subscribeToSaveResponse(this.utiProfileService.create(this.utiProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUtiProfileMySuffix>>) {
        result.subscribe((res: HttpResponse<IUtiProfileMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get utiProfile() {
        return this._utiProfile;
    }

    set utiProfile(utiProfile: IUtiProfileMySuffix) {
        this._utiProfile = utiProfile;
        this.creeLe = moment(utiProfile.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(utiProfile.modifLe).format(DATE_TIME_FORMAT);
    }
}
