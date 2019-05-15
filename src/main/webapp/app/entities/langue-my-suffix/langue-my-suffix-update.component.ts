import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ILangueMySuffix, LangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { LangueMySuffixService } from './langue-my-suffix.service';

@Component({
    selector: 'jhi-langue-my-suffix-update',
    templateUrl: './langue-my-suffix-update.component.html'
})
export class LangueMySuffixUpdateComponent implements OnInit {
    langue: ILangueMySuffix;
    isSaving: boolean;

    editForm = this.fb.group({
        id: [],
        langueID: [null, [Validators.required]],
        libelle: [null, [Validators.required, Validators.maxLength(20)]],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: []
    });

    constructor(protected langueService: LangueMySuffixService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ langue }) => {
            this.updateForm(langue);
            this.langue = langue;
        });
    }

    updateForm(langue: ILangueMySuffix) {
        this.editForm.patchValue({
            id: langue.id,
            langueID: langue.langueID,
            libelle: langue.libelle,
            estActif: langue.estActif,
            creeLe: langue.creeLe != null ? langue.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: langue.creePar,
            modifLe: langue.modifLe != null ? langue.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: langue.modifPar
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const langue = this.createFromForm();
        if (langue.id !== undefined) {
            this.subscribeToSaveResponse(this.langueService.update(langue));
        } else {
            this.subscribeToSaveResponse(this.langueService.create(langue));
        }
    }

    private createFromForm(): ILangueMySuffix {
        const entity = {
            ...new LangueMySuffix(),
            id: this.editForm.get(['id']).value,
            langueID: this.editForm.get(['langueID']).value,
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

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILangueMySuffix>>) {
        result.subscribe((res: HttpResponse<ILangueMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
