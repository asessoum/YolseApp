import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { ProfileMySuffixService } from './profile-my-suffix.service';
import { IUtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';
import { UtiProfileMySuffixService } from 'app/entities/uti-profile-my-suffix';

@Component({
    selector: 'jhi-profile-my-suffix-update',
    templateUrl: './profile-my-suffix-update.component.html'
})
export class ProfileMySuffixUpdateComponent implements OnInit {
    private _profile: IProfileMySuffix;
    isSaving: boolean;

    utiprofiles: IUtiProfileMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private profileService: ProfileMySuffixService,
        private utiProfileService: UtiProfileMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profile }) => {
            this.profile = profile;
        });
        this.utiProfileService.query().subscribe(
            (res: HttpResponse<IUtiProfileMySuffix[]>) => {
                this.utiprofiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUtiProfileById(index: number, item: IUtiProfileMySuffix) {
        return item.id;
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
