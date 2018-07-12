import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';
import { TypeEngraisMySuffixService } from './type-engrais-my-suffix.service';
import { IEngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';
import { EngraisClientMySuffixService } from 'app/entities/engrais-client-my-suffix';

@Component({
    selector: 'jhi-type-engrais-my-suffix-update',
    templateUrl: './type-engrais-my-suffix-update.component.html'
})
export class TypeEngraisMySuffixUpdateComponent implements OnInit {
    private _typeEngrais: ITypeEngraisMySuffix;
    isSaving: boolean;

    engraisclients: IEngraisClientMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private typeEngraisService: TypeEngraisMySuffixService,
        private engraisClientService: EngraisClientMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeEngrais }) => {
            this.typeEngrais = typeEngrais;
        });
        this.engraisClientService.query().subscribe(
            (res: HttpResponse<IEngraisClientMySuffix[]>) => {
                this.engraisclients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.typeEngrais.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.typeEngrais.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.typeEngrais.id !== undefined) {
            this.subscribeToSaveResponse(this.typeEngraisService.update(this.typeEngrais));
        } else {
            this.subscribeToSaveResponse(this.typeEngraisService.create(this.typeEngrais));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITypeEngraisMySuffix>>) {
        result.subscribe((res: HttpResponse<ITypeEngraisMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEngraisClientById(index: number, item: IEngraisClientMySuffix) {
        return item.id;
    }
    get typeEngrais() {
        return this._typeEngrais;
    }

    set typeEngrais(typeEngrais: ITypeEngraisMySuffix) {
        this._typeEngrais = typeEngrais;
        this.creeLe = moment(typeEngrais.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(typeEngrais.modifLe).format(DATE_TIME_FORMAT);
    }
}
