import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';
import { UtiProfilMySuffixService } from './uti-profil-my-suffix.service';

@Component({
    selector: 'jhi-uti-profil-my-suffix-update',
    templateUrl: './uti-profil-my-suffix-update.component.html'
})
export class UtiProfilMySuffixUpdateComponent implements OnInit {
    private _utiProfil: IUtiProfilMySuffix;
    isSaving: boolean;
    creeLe: string;
    modifLe: string;

    constructor(private utiProfilService: UtiProfilMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utiProfil }) => {
            this.utiProfil = utiProfil;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.utiProfil.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.utiProfil.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.utiProfil.id !== undefined) {
            this.subscribeToSaveResponse(this.utiProfilService.update(this.utiProfil));
        } else {
            this.subscribeToSaveResponse(this.utiProfilService.create(this.utiProfil));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUtiProfilMySuffix>>) {
        result.subscribe((res: HttpResponse<IUtiProfilMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get utiProfil() {
        return this._utiProfil;
    }

    set utiProfil(utiProfil: IUtiProfilMySuffix) {
        this._utiProfil = utiProfil;
        this.creeLe = moment(utiProfil.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(utiProfil.modifLe).format(DATE_TIME_FORMAT);
    }
}
