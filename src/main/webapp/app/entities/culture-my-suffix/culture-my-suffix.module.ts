import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    CultureMySuffixComponent,
    CultureMySuffixDetailComponent,
    CultureMySuffixUpdateComponent,
    CultureMySuffixDeletePopupComponent,
    CultureMySuffixDeleteDialogComponent,
    cultureRoute,
    culturePopupRoute
} from './';

const ENTITY_STATES = [...cultureRoute, ...culturePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CultureMySuffixComponent,
        CultureMySuffixDetailComponent,
        CultureMySuffixUpdateComponent,
        CultureMySuffixDeleteDialogComponent,
        CultureMySuffixDeletePopupComponent
    ],
    entryComponents: [
        CultureMySuffixComponent,
        CultureMySuffixUpdateComponent,
        CultureMySuffixDeleteDialogComponent,
        CultureMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppCultureMySuffixModule {}
