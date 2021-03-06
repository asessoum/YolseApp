import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';
import { BesoinIntrantMySuffixService } from './besoin-intrant-my-suffix.service';
import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';
import { ClientMySuffixService } from 'app/entities/client-my-suffix';
import { ICultureMySuffix } from 'app/shared/model/culture-my-suffix.model';
import { CultureMySuffixService } from 'app/entities/culture-my-suffix';

@Component({
    selector: 'jhi-besoin-intrant-my-suffix-update',
    templateUrl: './besoin-intrant-my-suffix-update.component.html'
})
export class BesoinIntrantMySuffixUpdateComponent implements OnInit {
    private _besoinIntrant: IBesoinIntrantMySuffix;
    isSaving: boolean;

    clients: IClientMySuffix[];

    cultures: ICultureMySuffix[];
    creeLe: string;
    modifLe: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private besoinIntrantService: BesoinIntrantMySuffixService,
        private clientService: ClientMySuffixService,
        private cultureService: CultureMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ besoinIntrant }) => {
            this.besoinIntrant = besoinIntrant;
        });
        this.clientService.query().subscribe(
            (res: HttpResponse<IClientMySuffix[]>) => {
                this.clients = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.cultureService.query().subscribe(
            (res: HttpResponse<ICultureMySuffix[]>) => {
                this.cultures = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.besoinIntrant.creeLe = moment(this.creeLe, DATE_TIME_FORMAT);
        this.besoinIntrant.modifLe = moment(this.modifLe, DATE_TIME_FORMAT);
        if (this.besoinIntrant.id !== undefined) {
            this.subscribeToSaveResponse(this.besoinIntrantService.update(this.besoinIntrant));
        } else {
            this.subscribeToSaveResponse(this.besoinIntrantService.create(this.besoinIntrant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBesoinIntrantMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IBesoinIntrantMySuffix>) => this.onSaveSuccess(),
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

    trackClientById(index: number, item: IClientMySuffix) {
        return item.id;
    }

    trackCultureById(index: number, item: ICultureMySuffix) {
        return item.id;
    }
    get besoinIntrant() {
        return this._besoinIntrant;
    }

    set besoinIntrant(besoinIntrant: IBesoinIntrantMySuffix) {
        this._besoinIntrant = besoinIntrant;
        this.creeLe = moment(besoinIntrant.creeLe).format(DATE_TIME_FORMAT);
        this.modifLe = moment(besoinIntrant.modifLe).format(DATE_TIME_FORMAT);
    }
}
