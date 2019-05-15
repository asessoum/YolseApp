import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    CategorieMySuffixComponent,
    CategorieMySuffixDetailComponent,
    CategorieMySuffixUpdateComponent,
    CategorieMySuffixDeletePopupComponent,
    CategorieMySuffixDeleteDialogComponent,
    categorieRoute,
    categoriePopupRoute
} from './';

const ENTITY_STATES = [...categorieRoute, ...categoriePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CategorieMySuffixComponent,
        CategorieMySuffixDetailComponent,
        CategorieMySuffixUpdateComponent,
        CategorieMySuffixDeleteDialogComponent,
        CategorieMySuffixDeletePopupComponent
    ],
    entryComponents: [
        CategorieMySuffixComponent,
        CategorieMySuffixUpdateComponent,
        CategorieMySuffixDeleteDialogComponent,
        CategorieMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppCategorieMySuffixModule {}
