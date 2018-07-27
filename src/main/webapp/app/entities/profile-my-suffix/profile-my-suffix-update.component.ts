import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { ProfileMySuffixService } from './profile-my-suffix.service';

@Component({
    selector: 'jhi-profile-my-suffix-update',
    templateUrl: './profile-my-suffix-update.component.html'
})
export class ProfileMySuffixUpdateComponent implements OnInit {
    private _profile: IProfileMySuffix;
    isSaving: boolean;
    creeLe: string;
    modifLe: string;

    constructor(private profileService: ProfileMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profile }) => {
            this.profile = profile;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.profile.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.profile.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.profile.id !== undefined) {
            this.subscribeToSaveResponse(this.profileService.update(this.profile));
        } else {
            this.subscribeToSaveResponse(this.profileService.create(this.profile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfileMySuffix>>) {
        result.subscribe((res: HttpResponse<IProfileMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get profile() {
        return this._profile;
    }

    set profile(profile: IProfileMySuffix) {
        this._profile = profile;
        this.creeLe = moment(profile.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(profile.modifLe).format(DATE_TIME_FORMAT);
    }
}
