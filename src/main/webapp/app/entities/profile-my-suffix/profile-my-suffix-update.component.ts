import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IProfileMySuffix, ProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { ProfileMySuffixService } from './profile-my-suffix.service';

@Component({
    selector: 'jhi-profile-my-suffix-update',
    templateUrl: './profile-my-suffix-update.component.html'
})
export class ProfileMySuffixUpdateComponent implements OnInit {
    profile: IProfileMySuffix;
    isSaving: boolean;

    editForm = this.fb.group({
        id: [],
        profileID: [null, [Validators.required]],
        libelle: [null, [Validators.required, Validators.maxLength(20)]],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: []
    });

    constructor(protected profileService: ProfileMySuffixService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profile }) => {
            this.updateForm(profile);
            this.profile = profile;
        });
    }

    updateForm(profile: IProfileMySuffix) {
        this.editForm.patchValue({
            id: profile.id,
            profileID: profile.profileID,
            libelle: profile.libelle,
            estActif: profile.estActif,
            creeLe: profile.creeLe != null ? profile.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: profile.creePar,
            modifLe: profile.modifLe != null ? profile.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: profile.modifPar
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const profile = this.createFromForm();
        if (profile.id !== undefined) {
            this.subscribeToSaveResponse(this.profileService.update(profile));
        } else {
            this.subscribeToSaveResponse(this.profileService.create(profile));
        }
    }

    private createFromForm(): IProfileMySuffix {
        const entity = {
            ...new ProfileMySuffix(),
            id: this.editForm.get(['id']).value,
            profileID: this.editForm.get(['profileID']).value,
            libelle: this.editForm.get(['libelle']).value,
            estActif: this.editForm.get(['estActif']).value,
            creeLe: this.editForm.get(['creeLe']).value != null ? moment(this.editForm.get(['creeLe']).value, DATE_TIME_FORMAT) : undefined,
            creePar: this.editForm.get(['creePar']).value,
            modifLe:
                this.editForm.get(['modifLe']).value != null ? moment(this.editForm.get(['modifLe']).value, DATE_TIME_FORMAT) : undefined,
            modifPar: this.editForm.get(['modifPar']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfileMySuffix>>) {
        result.subscribe((res: HttpResponse<IProfileMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
