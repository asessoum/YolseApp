import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ICategorieMySuffix, CategorieMySuffix } from 'app/shared/model/categorie-my-suffix.model';
import { CategorieMySuffixService } from './categorie-my-suffix.service';

@Component({
    selector: 'jhi-categorie-my-suffix-update',
    templateUrl: './categorie-my-suffix-update.component.html'
})
export class CategorieMySuffixUpdateComponent implements OnInit {
    categorie: ICategorieMySuffix;
    isSaving: boolean;

    editForm = this.fb.group({
        id: [],
        categorieID: [null, [Validators.required]],
        localID: [null, [Validators.required]],
        remoteID: [null, [Validators.required]],
        libelle: [null, [Validators.required, Validators.maxLength(20)]],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: []
    });

    constructor(protected categorieService: CategorieMySuffixService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ categorie }) => {
            this.updateForm(categorie);
            this.categorie = categorie;
        });
    }

    updateForm(categorie: ICategorieMySuffix) {
        this.editForm.patchValue({
            id: categorie.id,
            categorieID: categorie.categorieID,
            localID: categorie.localID,
            remoteID: categorie.remoteID,
            libelle: categorie.libelle,
            estActif: categorie.estActif,
            creeLe: categorie.creeLe != null ? categorie.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: categorie.creePar,
            modifLe: categorie.modifLe != null ? categorie.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: categorie.modifPar
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const categorie = this.createFromForm();
        if (categorie.id !== undefined) {
            this.subscribeToSaveResponse(this.categorieService.update(categorie));
        } else {
            this.subscribeToSaveResponse(this.categorieService.create(categorie));
        }
    }

    private createFromForm(): ICategorieMySuffix {
        const entity = {
            ...new CategorieMySuffix(),
            id: this.editForm.get(['id']).value,
            categorieID: this.editForm.get(['categorieID']).value,
            localID: this.editForm.get(['localID']).value,
            remoteID: this.editForm.get(['remoteID']).value,
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

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieMySuffix>>) {
        result.subscribe((res: HttpResponse<ICategorieMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
