import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    LangueMySuffixComponent,
    LangueMySuffixDetailComponent,
    LangueMySuffixUpdateComponent,
    LangueMySuffixDeletePopupComponent,
    LangueMySuffixDeleteDialogComponent,
    langueRoute,
    languePopupRoute
} from './';

const ENTITY_STATES = [...langueRoute, ...languePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LangueMySuffixComponent,
        LangueMySuffixDetailComponent,
        LangueMySuffixUpdateComponent,
        LangueMySuffixDeleteDialogComponent,
        LangueMySuffixDeletePopupComponent
    ],
    entryComponents: [
        LangueMySuffixComponent,
        LangueMySuffixUpdateComponent,
        LangueMySuffixDeleteDialogComponent,
        LangueMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppLangueMySuffixModule {}
