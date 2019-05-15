import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ITransactionMySuffix, TransactionMySuffix } from 'app/shared/model/transaction-my-suffix.model';
import { TransactionMySuffixService } from './transaction-my-suffix.service';
import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';
import { ClientMySuffixService } from 'app/entities/client-my-suffix';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from 'app/entities/utilisateur-my-suffix';

@Component({
    selector: 'jhi-transaction-my-suffix-update',
    templateUrl: './transaction-my-suffix-update.component.html'
})
export class TransactionMySuffixUpdateComponent implements OnInit {
    transaction: ITransactionMySuffix;
    isSaving: boolean;

    clients: IClientMySuffix[];

    utilisateurs: IUtilisateurMySuffix[];

    editForm = this.fb.group({
        id: [],
        transactionID: [null, [Validators.required]],
        localID: [null, [Validators.required]],
        remoteID: [null, [Validators.required]],
        venteID: [null, [Validators.required]],
        vendeurID: [null, [Validators.required]],
        quantiteTotal: [null, [Validators.required]],
        prixTotalTransaction: [null, [Validators.required]],
        validSup: [],
        validRes: [],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: [],
        clientId: [],
        vendeurIDId: []
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected transactionService: TransactionMySuffixService,
        protected clientService: ClientMySuffixService,
        protected utilisateurService: UtilisateurMySuffixService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transaction }) => {
            this.updateForm(transaction);
            this.transaction = transaction;
        });
        this.clientService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IClientMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IClientMySuffix[]>) => response.body)
            )
            .subscribe((res: IClientMySuffix[]) => (this.clients = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.utilisateurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUtilisateurMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUtilisateurMySuffix[]>) => response.body)
            )
            .subscribe((res: IUtilisateurMySuffix[]) => (this.utilisateurs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    updateForm(transaction: ITransactionMySuffix) {
        this.editForm.patchValue({
            id: transaction.id,
            transactionID: transaction.transactionID,
            localID: transaction.localID,
            remoteID: transaction.remoteID,
            venteID: transaction.venteID,
            vendeurID: transaction.vendeurID,
            quantiteTotal: transaction.quantiteTotal,
            prixTotalTransaction: transaction.prixTotalTransaction,
            validSup: transaction.validSup,
            validRes: transaction.validRes,
            estActif: transaction.estActif,
            creeLe: transaction.creeLe != null ? transaction.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: transaction.creePar,
            modifLe: transaction.modifLe != null ? transaction.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: transaction.modifPar,
            clientId: transaction.clientId,
            vendeurIDId: transaction.vendeurIDId
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const transaction = this.createFromForm();
        if (transaction.id !== undefined) {
            this.subscribeToSaveResponse(this.transactionService.update(transaction));
        } else {
            this.subscribeToSaveResponse(this.transactionService.create(transaction));
        }
    }

    private createFromForm(): ITransactionMySuffix {
        const entity = {
            ...new TransactionMySuffix(),
            id: this.editForm.get(['id']).value,
            transactionID: this.editForm.get(['transactionID']).value,
            localID: this.editForm.get(['localID']).value,
            remoteID: this.editForm.get(['remoteID']).value,
            venteID: this.editForm.get(['venteID']).value,
            vendeurID: this.editForm.get(['vendeurID']).value,
            quantiteTotal: this.editForm.get(['quantiteTotal']).value,
            prixTotalTransaction: this.editForm.get(['prixTotalTransaction']).value,
            validSup: this.editForm.get(['validSup']).value,
            validRes: this.editForm.get(['validRes']).value,
            estActif: this.editForm.get(['estActif']).value,
            creeLe: this.editForm.get(['creeLe']).value != null ? moment(this.editForm.get(['creeLe']).value, DATE_TIME_FORMAT) : undefined,
            creePar: this.editForm.get(['creePar']).value,
            modifLe:
                this.editForm.get(['modifLe']).value != null ? moment(this.editForm.get(['modifLe']).value, DATE_TIME_FORMAT) : undefined,
            modifPar: this.editForm.get(['modifPar']).value,
            clientId: this.editForm.get(['clientId']).value,
            vendeurIDId: this.editForm.get(['vendeurIDId']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransactionMySuffix>>) {
        result.subscribe((res: HttpResponse<ITransactionMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackClientById(index: number, item: IClientMySuffix) {
        return item.id;
    }

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }
}
