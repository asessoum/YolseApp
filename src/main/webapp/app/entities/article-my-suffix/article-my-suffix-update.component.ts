import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IArticleMySuffix, ArticleMySuffix } from 'app/shared/model/article-my-suffix.model';
import { ArticleMySuffixService } from './article-my-suffix.service';
import { ICategorieMySuffix } from 'app/shared/model/categorie-my-suffix.model';
import { CategorieMySuffixService } from 'app/entities/categorie-my-suffix';

@Component({
    selector: 'jhi-article-my-suffix-update',
    templateUrl: './article-my-suffix-update.component.html'
})
export class ArticleMySuffixUpdateComponent implements OnInit {
    article: IArticleMySuffix;
    isSaving: boolean;

    categories: ICategorieMySuffix[];

    editForm = this.fb.group({
        id: [],
        articleID: [null, [Validators.required, Validators.maxLength(10)]],
        localID: [null, [Validators.required]],
        remoteID: [null, [Validators.required]],
        libelle: [null, [Validators.required, Validators.maxLength(20)]],
        description: [null, [Validators.maxLength(200)]],
        prixDeVente: [],
        prixDeRevient: [],
        margeBrute: [],
        estActif: [],
        creeLe: [],
        creePar: [],
        modifLe: [],
        modifPar: [],
        categorieId: []
    });

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected articleService: ArticleMySuffixService,
        protected categorieService: CategorieMySuffixService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ article }) => {
            this.updateForm(article);
            this.article = article;
        });
        this.categorieService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICategorieMySuffix[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICategorieMySuffix[]>) => response.body)
            )
            .subscribe((res: ICategorieMySuffix[]) => (this.categories = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    updateForm(article: IArticleMySuffix) {
        this.editForm.patchValue({
            id: article.id,
            articleID: article.articleID,
            localID: article.localID,
            remoteID: article.remoteID,
            libelle: article.libelle,
            description: article.description,
            prixDeVente: article.prixDeVente,
            prixDeRevient: article.prixDeRevient,
            margeBrute: article.margeBrute,
            estActif: article.estActif,
            creeLe: article.creeLe != null ? article.creeLe.format(DATE_TIME_FORMAT) : null,
            creePar: article.creePar,
            modifLe: article.modifLe != null ? article.modifLe.format(DATE_TIME_FORMAT) : null,
            modifPar: article.modifPar,
            categorieId: article.categorieId
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const article = this.createFromForm();
        if (article.id !== undefined) {
            this.subscribeToSaveResponse(this.articleService.update(article));
        } else {
            this.subscribeToSaveResponse(this.articleService.create(article));
        }
    }

    private createFromForm(): IArticleMySuffix {
        const entity = {
            ...new ArticleMySuffix(),
            id: this.editForm.get(['id']).value,
            articleID: this.editForm.get(['articleID']).value,
            localID: this.editForm.get(['localID']).value,
            remoteID: this.editForm.get(['remoteID']).value,
            libelle: this.editForm.get(['libelle']).value,
            description: this.editForm.get(['description']).value,
            prixDeVente: this.editForm.get(['prixDeVente']).value,
            prixDeRevient: this.editForm.get(['prixDeRevient']).value,
            margeBrute: this.editForm.get(['margeBrute']).value,
            estActif: this.editForm.get(['estActif']).value,
            creeLe: this.editForm.get(['creeLe']).value != null ? moment(this.editForm.get(['creeLe']).value, DATE_TIME_FORMAT) : undefined,
            creePar: this.editForm.get(['creePar']).value,
            modifLe:
                this.editForm.get(['modifLe']).value != null ? moment(this.editForm.get(['modifLe']).value, DATE_TIME_FORMAT) : undefined,
            modifPar: this.editForm.get(['modifPar']).value,
            categorieId: this.editForm.get(['categorieId']).value
        };
        return entity;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IArticleMySuffix>>) {
        result.subscribe((res: HttpResponse<IArticleMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCategorieById(index: number, item: ICategorieMySuffix) {
        return item.id;
    }
}
