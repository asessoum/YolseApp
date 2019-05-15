import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IVenteMySuffix, VenteMySuffix } from 'app/shared/model/vente-my-suffix.model';
import { VenteMySuffixService } from './vente-my-suffix.service';
import { ITransactionMySuffix } from 'app/shared/model/transaction-my-suffix.model';
import { TransactionMySuffixService } from 'app/entities/transaction-my-suffix';
import { IArticleMySuffix } from 'app/shared/model/article-my-suffix.model';
import { ArticleMySuffixService } from 'app/entities/article-my-suffix';

@Component({
    selector: 'jhi-vente-my-suffix-update',
    templateUrl: './vente-my-suffix-update.component.html'
})
export class VenteMySuffixUpdateComponent implements OnInit {
    vente: IVenteMySuffix;
    isSaving: boolean;

    transactions: ITransactionMySuffix[];

    articles: IArticleMySuffix[];

    editForm = this.fb.group({
        id: [],
        venteID: [null, [Validators.required]],
        localID: [null, [Validators.required]],
        remoteID: [null, [Validators.required]],
        quantite: [null, [Validators.required]],
        prixVente: [null, [Validators.required]],
        margeVente: [null, [Validators.required]],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: [],
        transactionId: [],
        articleId: []
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected venteService: VenteMySuffixService,
        protected transactionService: TransactionMySuffixService,
        protected articleService: ArticleMySuffixService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vente }) => {
            this.updateForm(vente);
            this.vente = vente;
        });
        this.transactionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITransactionMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITransactionMySuffix[]>) => response.body)
            )
            .subscribe((res: ITransactionMySuffix[]) => (this.transactions = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.articleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IArticleMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IArticleMySuffix[]>) => response.body)
            )
            .subscribe((res: IArticleMySuffix[]) => (this.articles = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    updateForm(vente: IVenteMySuffix) {
        this.editForm.patchValue({
            id: vente.id,
            venteID: vente.venteID,
            localID: vente.localID,
            remoteID: vente.remoteID,
            quantite: vente.quantite,
            prixVente: vente.prixVente,
            margeVente: vente.margeVente,
            creeLe: vente.creeLe != null ? vente.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: vente.creePar,
            modifLe: vente.modifLe != null ? vente.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: vente.modifPar,
            transactionId: vente.transactionId,
            articleId: vente.articleId
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const vente = this.createFromForm();
        if (vente.id !== undefined) {
            this.subscribeToSaveResponse(this.venteService.update(vente));
        } else {
            this.subscribeToSaveResponse(this.venteService.create(vente));
        }
    }

    private createFromForm(): IVenteMySuffix {
        const entity = {
            ...new VenteMySuffix(),
            id: this.editForm.get(['id']).value,
            venteID: this.editForm.get(['venteID']).value,
            localID: this.editForm.get(['localID']).value,
            remoteID: this.editForm.get(['remoteID']).value,
            quantite: this.editForm.get(['quantite']).value,
            prixVente: this.editForm.get(['prixVente']).value,
            margeVente: this.editForm.get(['margeVente']).value,
            creeLe: this.editForm.get(['creeLe']).value != null ? moment(this.editForm.get(['creeLe']).value, DATE_TIME_FORMAT) : undefined,
            creePar: this.editForm.get(['creePar']).value,
            modifLe:
                this.editForm.get(['modifLe']).value != null ? moment(this.editForm.get(['modifLe']).value, DATE_TIME_FORMAT) : undefined,
            modifPar: this.editForm.get(['modifPar']).value,
            transactionId: this.editForm.get(['transactionId']).value,
            articleId: this.editForm.get(['articleId']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVenteMySuffix>>) {
        result.subscribe((res: HttpResponse<IVenteMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTransactionById(index: number, item: ITransactionMySuffix) {
        return item.id;
    }

    trackArticleById(index: number, item: IArticleMySuffix) {
        return item.id;
    }
}
