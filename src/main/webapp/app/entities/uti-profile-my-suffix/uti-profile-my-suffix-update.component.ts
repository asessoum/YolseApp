import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IUtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';
import { UtiProfileMySuffixService } from './uti-profile-my-suffix.service';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from 'app/entities/utilisateur-my-suffix';
import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { ProfileMySuffixService } from 'app/entities/profile-my-suffix';

@Component({
    selector: 'jhi-uti-profile-my-suffix-update',
    templateUrl: './uti-profile-my-suffix-update.component.html'
})
export class UtiProfileMySuffixUpdateComponent implements OnInit {
    private _utiProfile: IUtiProfileMySuffix;
    isSaving: boolean;

    utilisateurs: IUtilisateurMySuffix[];

    profiles: IProfileMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private utiProfileService: UtiProfileMySuffixService,
        private utilisateurService: UtilisateurMySuffixService,
        private profileService: ProfileMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utiProfile }) => {
            this.utiProfile = utiProfile;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateurMySuffix[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.profileService.query().subscribe(
            (res: HttpResponse<IProfileMySuffix[]>) => {
                this.profiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }

    trackProfileById(index: number, item: IProfileMySuffix) {
        return item.id;
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
