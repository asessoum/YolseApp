import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IPaysMySuffix, PaysMySuffix } from 'app/shared/model/pays-my-suffix.model';
import { PaysMySuffixService } from './pays-my-suffix.service';

@Component({
    selector: 'jhi-pays-my-suffix-update',
    templateUrl: './pays-my-suffix-update.component.html'
})
export class PaysMySuffixUpdateComponent implements OnInit {
    pays: IPaysMySuffix;
    isSaving: boolean;

    editForm = this.fb.group({
        id: [],
        paysID: [null, [Validators.required]],
        nomPays: [null, [Validators.required, Validators.maxLength(20)]],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: []
    });

    constructor(protected paysService: PaysMySuffixService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pays }) => {
            this.updateForm(pays);
            this.pays = pays;
        });
    }

    updateForm(pays: IPaysMySuffix) {
        this.editForm.patchValue({
            id: pays.id,
            paysID: pays.paysID,
            nomPays: pays.nomPays,
            estActif: pays.estActif,
            creeLe: pays.creeLe != null ? pays.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: pays.creePar,
            modifLe: pays.modifLe != null ? pays.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: pays.modifPar
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const pays = this.createFromForm();
        if (pays.id !== undefined) {
            this.subscribeToSaveResponse(this.paysService.update(pays));
        } else {
            this.subscribeToSaveResponse(this.paysService.create(pays));
        }
    }

    private createFromForm(): IPaysMySuffix {
        const entity = {
            ...new PaysMySuffix(),
            id: this.editForm.get(['id']).value,
            paysID: this.editForm.get(['paysID']).value,
            nomPays: this.editForm.get(['nomPays']).value,
            estActif: this.editForm.get(['estActif']).value,
            creeLe: this.editForm.get(['creeLe']).value != null ? moment(this.editForm.get(['creeLe']).value, DATE_TIME_FORMAT) : undefined,
            creePar: this.editForm.get(['creePar']).value,
            modifLe:
                this.editForm.get(['modifLe']).value != null ? moment(this.editForm.get(['modifLe']).value, DATE_TIME_FORMAT) : undefined,
            modifPar: this.editForm.get(['modifPar']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaysMySuffix>>) {
        result.subscribe((res: HttpResponse<IPaysMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
