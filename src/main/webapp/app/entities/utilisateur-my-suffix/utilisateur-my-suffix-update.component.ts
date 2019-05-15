import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IUtilisateurMySuffix, UtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from './utilisateur-my-suffix.service';
import { ILangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { LangueMySuffixService } from 'app/entities/langue-my-suffix';
import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';
import { CommuneMySuffixService } from 'app/entities/commune-my-suffix';

@Component({
    selector: 'jhi-utilisateur-my-suffix-update',
    templateUrl: './utilisateur-my-suffix-update.component.html'
})
export class UtilisateurMySuffixUpdateComponent implements OnInit {
    utilisateur: IUtilisateurMySuffix;
    isSaving: boolean;

    langues: ILangueMySuffix[];

    communes: ICommuneMySuffix[];

    utilisateurs: IUtilisateurMySuffix[];

    editForm = this.fb.group({
        id: [],
        userID: [null, [Validators.required]],
        login: [null, [Validators.required, Validators.maxLength(6)]],
        mdp: [null, [Validators.required, Validators.maxLength(8)]],
        nom: [null, [Validators.required, Validators.maxLength(20)]],
        prenom: [null, [Validators.required, Validators.maxLength(20)]],
        dateNaiss: [null, [Validators.required]],
        genre: [null, [Validators.required]],
        tel: [null, [Validators.required, Validators.maxLength(10)]],
        email: [null, [Validators.maxLength(50)]],
        numCarteUti: [null, [Validators.required, Validators.maxLength(20)]],
        dateCarteUti: [],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: [],
        langueId: [],
        communeId: [],
        responsableId: []
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected utilisateurService: UtilisateurMySuffixService,
        protected langueService: LangueMySuffixService,
        protected communeService: CommuneMySuffixService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utilisateur }) => {
            this.updateForm(utilisateur);
            this.utilisateur = utilisateur;
        });
        this.langueService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ILangueMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ILangueMySuffix[]>) => response.body)
            )
            .subscribe((res: ILangueMySuffix[]) => (this.langues = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.communeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICommuneMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICommuneMySuffix[]>) => response.body)
            )
            .subscribe((res: ICommuneMySuffix[]) => (this.communes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.utilisateurService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUtilisateurMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUtilisateurMySuffix[]>) => response.body)
            )
            .subscribe((res: IUtilisateurMySuffix[]) => (this.utilisateurs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    updateForm(utilisateur: IUtilisateurMySuffix) {
        this.editForm.patchValue({
            id: utilisateur.id,
            userID: utilisateur.userID,
            login: utilisateur.login,
            mdp: utilisateur.mdp,
            nom: utilisateur.nom,
            prenom: utilisateur.prenom,
            dateNaiss: utilisateur.dateNaiss != null ? utilisateur.dateNaiss.format(DATE_TIME_FORMAT) : null,
            genre: utilisateur.genre,
            tel: utilisateur.tel,
            email: utilisateur.email,
            numCarteUti: utilisateur.numCarteUti,
            dateCarteUti: utilisateur.dateCarteUti != null ? utilisateur.dateCarteUti.format(DATE_TIME_FORMAT) : null,
            estActif: utilisateur.estActif,
            creeLe: utilisateur.creeLe != null ? utilisateur.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: utilisateur.creePar,
            modifLe: utilisateur.modifLe != null ? utilisateur.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: utilisateur.modifPar,
            langueId: utilisateur.langueId,
            communeId: utilisateur.communeId,
            responsableId: utilisateur.responsableId
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const utilisateur = this.createFromForm();
        if (utilisateur.id !== undefined) {
            this.subscribeToSaveResponse(this.utilisateurService.update(utilisateur));
        } else {
            this.subscribeToSaveResponse(this.utilisateurService.create(utilisateur));
        }
    }

    private createFromForm(): IUtilisateurMySuffix {
        const entity = {
            ...new UtilisateurMySuffix(),
            id: this.editForm.get(['id']).value,
            userID: this.editForm.get(['userID']).value,
            login: this.editForm.get(['login']).value,
            mdp: this.editForm.get(['mdp']).value,
            nom: this.editForm.get(['nom']).value,
            prenom: this.editForm.get(['prenom']).value,
            dateNaiss:
                this.editForm.get(['dateNaiss']).value != null
                    ? moment(this.editForm.get(['dateNaiss']).value, DATE_TIME_FORMAT)
                    : undefined,
            genre: this.editForm.get(['genre']).value,
            tel: this.editForm.get(['tel']).value,
            email: this.editForm.get(['email']).value,
            numCarteUti: this.editForm.get(['numCarteUti']).value,
            dateCarteUti:
                this.editForm.get(['dateCarteUti']).value != null
                    ? moment(this.editForm.get(['dateCarteUti']).value, DATE_TIME_FORMAT)
                    : undefined,
            estActif: this.editForm.get(['estActif']).value,
            creeLe: this.editForm.get(['creeLe']).value != null ? moment(this.editForm.get(['creeLe']).value, DATE_TIME_FORMAT) : undefined,
            creePar: this.editForm.get(['creePar']).value,
            modifLe:
                this.editForm.get(['modifLe']).value != null ? moment(this.editForm.get(['modifLe']).value, DATE_TIME_FORMAT) : undefined,
            modifPar: this.editForm.get(['modifPar']).value,
            langueId: this.editForm.get(['langueId']).value,
            communeId: this.editForm.get(['communeId']).value,
            responsableId: this.editForm.get(['responsableId']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IUtilisateurMySuffix>>) {
        result.subscribe((res: HttpResponse<IUtilisateurMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCommuneById(index: number, item: ICommuneMySuffix) {
        return item.id;
    }

    trackUtilisateurById(index: number, item: IUtilisateurMySuffix) {
        return item.id;
    }
}
