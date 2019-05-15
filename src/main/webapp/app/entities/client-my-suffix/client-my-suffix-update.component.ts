import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IClientMySuffix, ClientMySuffix } from 'app/shared/model/client-my-suffix.model';
import { ClientMySuffixService } from './client-my-suffix.service';
import { ILangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { LangueMySuffixService } from 'app/entities/langue-my-suffix';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from 'app/entities/utilisateur-my-suffix';
import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';
import { CommuneMySuffixService } from 'app/entities/commune-my-suffix';

@Component({
    selector: 'jhi-client-my-suffix-update',
    templateUrl: './client-my-suffix-update.component.html'
})
export class ClientMySuffixUpdateComponent implements OnInit {
    client: IClientMySuffix;
    isSaving: boolean;

    langues: ILangueMySuffix[];

    utilisateurs: IUtilisateurMySuffix[];

    communes: ICommuneMySuffix[];

    editForm = this.fb.group({
        id: [],
        clientID: [null, [Validators.required, Validators.maxLength(13)]],
        localID: [null, [Validators.required]],
        remoteID: [null, [Validators.required]],
        nom: [null, [Validators.required, Validators.maxLength(20)]],
        prenom: [null, [Validators.required, Validators.maxLength(20)]],
        naissance: [null, [Validators.required]],
        genre: [null, [Validators.required]],
        numCarteCli: [null, [Validators.required, Validators.maxLength(20)]],
        dCarteUtil: [null, [Validators.required]],
        tel: [null, [Validators.required, Validators.maxLength(10)]],
        email: [null, [Validators.maxLength(50)]],
        photoID: [null, [Validators.maxLength(200)]],
        infoSupplementaires: [null, [Validators.maxLength(500)]],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: [],
        langueId: [],
        commercialId: [],
        communeId: []
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected clientService: ClientMySuffixService,
        protected langueService: LangueMySuffixService,
        protected utilisateurService: UtilisateurMySuffixService,
        protected communeService: CommuneMySuffixService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ client }) => {
            this.updateForm(client);
            this.client = client;
        });
        this.langueService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ILangueMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ILangueMySuffix[]>) => response.body)
            )
            .subscribe((res: ILangueMySuffix[]) => (this.langues = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.utilisateurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUtilisateurMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUtilisateurMySuffix[]>) => response.body)
            )
            .subscribe((res: IUtilisateurMySuffix[]) => (this.utilisateurs = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.communeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICommuneMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICommuneMySuffix[]>) => response.body)
            )
            .subscribe((res: ICommuneMySuffix[]) => (this.communes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    updateForm(client: IClientMySuffix) {
        this.editForm.patchValue({
            id: client.id,
            clientID: client.clientID,
            localID: client.localID,
            remoteID: client.remoteID,
            nom: client.nom,
            prenom: client.prenom,
            naissance: client.naissance != null ? client.naissance.format(DATE_TIME_FORMAT) : null,
            genre: client.genre,
            numCarteCli: client.numCarteCli,
            dCarteUtil: client.dCarteUtil != null ? client.dCarteUtil.format(DATE_TIME_FORMAT) : null,
            tel: client.tel,
            email: client.email,
            photoID: client.photoID,
            infoSupplementaires: client.infoSupplementaires,
            estActif: client.estActif,
            creeLe: client.creeLe != null ? client.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: client.creePar,
            modifLe: client.modifLe != null ? client.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: client.modifPar,
            langueId: client.langueId,
            commercialId: client.commercialId,
            communeId: client.communeId
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const client = this.createFromForm();
        if (client.id !== undefined) {
            this.subscribeToSaveResponse(this.clientService.update(client));
        } else {
            this.subscribeToSaveResponse(this.clientService.create(client));
        }
    }

    private createFromForm(): IClientMySuffix {
        const entity = {
            ...new ClientMySuffix(),
            id: this.editForm.get(['id']).value,
            clientID: this.editForm.get(['clientID']).value,
            localID: this.editForm.get(['localID']).value,
            remoteID: this.editForm.get(['remoteID']).value,
            nom: this.editForm.get(['nom']).value,
            prenom: this.editForm.get(['prenom']).value,
            naissance:
                this.editForm.get(['naissance']).value != null
                    ? moment(this.editForm.get(['naissance']).value, DATE_TIME_FORMAT)
                    : undefined,
            genre: this.editForm.get(['genre']).value,
            numCarteCli: this.editForm.get(['numCarteCli']).value,
            dCarteUtil:
                this.editForm.get(['dCarteUtil']).value != null
                    ? moment(this.editForm.get(['dCarteUtil']).value, DATE_TIME_FORMAT)
                    : undefined,
            tel: this.editForm.get(['tel']).value,
            email: this.editForm.get(['email']).value,
            photoID: this.editForm.get(['photoID']).value,
            infoSupplementaires: this.editForm.get(['infoSupplementaires']).value,
            estActif: this.editForm.get(['estActif']).value,
            creeLe: this.editForm.get(['creeLe']).value != null ? moment(this.editForm.get(['creeLe']).value, DATE_TIME_FORMAT) : undefined,
            creePar: this.editForm.get(['creePar']).value,
            modifLe:
                this.editForm.get(['modifLe']).value != null ? moment(this.editForm.get(['modifLe']).value, DATE_TIME_FORMAT) : undefined,
            modifPar: this.editForm.get(['modifPar']).value,
            langueId: this.editForm.get(['langueId']).value,
            commercialId: this.editForm.get(['commercialId']).value,
            communeId: this.editForm.get(['communeId']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IClientMySuffix>>) {
        result.subscribe((res: HttpResponse<IClientMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackLangueById(index: number, item: ILangueMySuffix) {
        return item.id;
    }

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }

    trackCommuneById(index: number, item: ICommuneMySuffix) {
        return item.id;
    }
}
