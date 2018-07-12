import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';
import { ProfilMySuffixService } from './profil-my-suffix.service';
import { IUtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';
import { UtiProfilMySuffixService } from 'app/entities/uti-profil-my-suffix';

@Component({
    selector: 'jhi-profil-my-suffix-update',
    templateUrl: './profil-my-suffix-update.component.html'
})
export class ProfilMySuffixUpdateComponent implements OnInit {
    private _profil: IProfilMySuffix;
    isSaving: boolean;

    utiprofils: IUtiProfilMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private profilService: ProfilMySuffixService,
        private utiProfilService: UtiProfilMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profil }) => {
            this.profil = profil;
        });
        this.utiProfilService.query().subscribe(
            (res: HttpResponse<IUtiProfilMySuffix[]>) => {
                this.utiprofils = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.profil.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.profil.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.profil.id !== undefined) {
            this.subscribeToSaveResponse(this.profilService.update(this.profil));
        } else {
            this.subscribeToSaveResponse(this.profilService.create(this.profil));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfilMySuffix>>) {
        result.subscribe((res: HttpResponse<IProfilMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUtiProfilById(index: number, item: IUtiProfilMySuffix) {
        return item.id;
    }
    get profil() {
        return this._profil;
    }

    set profil(profil: IProfilMySuffix) {
        this._profil = profil;
        this.creeLe = moment(profil.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(profil.modifLe).format(DATE_TIME_FORMAT);
    }
}
