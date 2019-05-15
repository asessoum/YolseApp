import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IUtiProfileMySuffix, UtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';
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
    utiProfile: IUtiProfileMySuffix;
    isSaving: boolean;

    utilisateurs: IUtilisateurMySuffix[];

    profiles: IProfileMySuffix[];

    editForm = this.fb.group({
        id: [],
        utiProID: [null, [Validators.required]],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: [],
        utilisateurId: [],
        profileId: []
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected utiProfileService: UtiProfileMySuffixService,
        protected utilisateurService: UtilisateurMySuffixService,
        protected profileService: ProfileMySuffixService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utiProfile }) => {
            this.updateForm(utiProfile);
            this.utiProfile = utiProfile;
        });
        this.utilisateurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUtilisateurMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUtilisateurMySuffix[]>) => response.body)
            )
            .subscribe((res: IUtilisateurMySuffix[]) => (this.utilisateurs = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.profileService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IProfileMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IProfileMySuffix[]>) => response.body)
            )
            .subscribe((res: IProfileMySuffix[]) => (this.profiles = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    updateForm(utiProfile: IUtiProfileMySuffix) {
        this.editForm.patchValue({
            id: utiProfile.id,
            utiProID: utiProfile.utiProID,
            estActif: utiProfile.estActif,
            creeLe: utiProfile.creeLe != null ? utiProfile.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: utiProfile.creePar,
            modifLe: utiProfile.modifLe != null ? utiProfile.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: utiProfile.modifPar,
            utilisateurId: utiProfile.utilisateurId,
            profileId: utiProfile.profileId
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const utiProfile = this.createFromForm();
        if (utiProfile.id !== undefined) {
            this.subscribeToSaveResponse(this.utiProfileService.update(utiProfile));
        } else {
            this.subscribeToSaveResponse(this.utiProfileService.create(utiProfile));
        }
    }

    private createFromForm(): IUtiProfileMySuffix {
        const entity = {
            ...new UtiProfileMySuffix(),
            id: this.editForm.get(['id']).value,
            utiProID: this.editForm.get(['utiProID']).value,
            estActif: this.editForm.get(['estActif']).value,
            creeLe: this.editForm.get(['creeLe']).value != null ? moment(this.editForm.get(['creeLe']).value, DATE_TIME_FORMAT) : undefined,
            creePar: this.editForm.get(['creePar']).value,
            modifLe:
                this.editForm.get(['modifLe']).value != null ? moment(this.editForm.get(['modifLe']).value, DATE_TIME_FORMAT) : undefined,
            modifPar: this.editForm.get(['modifPar']).value,
            utilisateurId: this.editForm.get(['utilisateurId']).value,
            profileId: this.editForm.get(['profileId']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IUtiProfileMySuffix>>) {
        result.subscribe((res: HttpResponse<IUtiProfileMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }

    trackProfileById(index: number, item: IProfileMySuffix) {
        return item.id;
    }
}
