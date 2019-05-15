import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    ArticleMySuffixComponent,
    ArticleMySuffixDetailComponent,
    ArticleMySuffixUpdateComponent,
    ArticleMySuffixDeletePopupComponent,
    ArticleMySuffixDeleteDialogComponent,
    articleRoute,
    articlePopupRoute
} from './';

const ENTITY_STATES = [...articleRoute, ...articlePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ArticleMySuffixComponent,
        ArticleMySuffixDetailComponent,
        ArticleMySuffixUpdateComponent,
        ArticleMySuffixDeleteDialogComponent,
        ArticleMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ArticleMySuffixComponent,
        ArticleMySuffixUpdateComponent,
        ArticleMySuffixDeleteDialogComponent,
        ArticleMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppArticleMySuffixModule {}
