import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IBesoinEngraisMySuffix } from 'app/shared/model/besoin-engrais-my-suffix.model';
import { BesoinEngraisMySuffixService } from './besoin-engrais-my-suffix.service';
import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';
import { BesoinIntrantMySuffixService } from 'app/entities/besoin-intrant-my-suffix';
import { IEngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';
import { EngraisMySuffixService } from 'app/entities/engrais-my-suffix';

@Component({
    selector: 'jhi-besoin-engrais-my-suffix-update',
    templateUrl: './besoin-engrais-my-suffix-update.component.html'
})
export class BesoinEngraisMySuffixUpdateComponent implements OnInit {
    private _besoinEngrais: IBesoinEngraisMySuffix;
    isSaving: boolean;

    besoinintrants: IBesoinIntrantMySuffix[];

    engrais: IEngraisMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private besoinEngraisService: BesoinEngraisMySuffixService,
        private besoinIntrantService: BesoinIntrantMySuffixService,
        private engraisService: EngraisMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ besoinEngrais }) => {
            this.besoinEngrais = besoinEngrais;
        });
        this.besoinIntrantService.query().subscribe(
            (res: HttpResponse<IBesoinIntrantMySuffix[]>) => {
                this.besoinintrants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.engraisService.query().subscribe(
            (res: HttpResponse<IEngraisMySuffix[]>) => {
                this.engrais = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.besoinEngrais.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.besoinEngrais.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.besoinEngrais.id !== undefined) {
            this.subscribeToSaveResponse(this.besoinEngraisService.update(this.besoinEngrais));
        } else {
            this.subscribeToSaveResponse(this.besoinEngraisService.create(this.besoinEngrais));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBesoinEngraisMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IBesoinEngraisMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackBesoinIntrantById(index: number, item: IBesoinIntrantMySuffix) {
        return item.id;
    }

    trackEngraisById(index: number, item: IEngraisMySuffix) {
        return item.id;
    }
    get besoinEngrais() {
        return this._besoinEngrais;
    }

    set besoinEngrais(besoinEngrais: IBesoinEngraisMySuffix) {
        this._besoinEngrais = besoinEngrais;
        this.creeLe = moment(besoinEngrais.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(besoinEngrais.modifLe).format(DATE_TIME_FORMAT);
    }
}
