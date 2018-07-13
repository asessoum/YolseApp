import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    TypeCultureMySuffixComponent,
    TypeCultureMySuffixDetailComponent,
    TypeCultureMySuffixUpdateComponent,
    TypeCultureMySuffixDeletePopupComponent,
    TypeCultureMySuffixDeleteDialogComponent,
    typeCultureRoute,
    typeCulturePopupRoute
} from './';

const ENTITY_STATES = [...typeCultureRoute, ...typeCulturePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeCultureMySuffixComponent,
        TypeCultureMySuffixDetailComponent,
        TypeCultureMySuffixUpdateComponent,
        TypeCultureMySuffixDeleteDialogComponent,
        TypeCultureMySuffixDeletePopupComponent
    ],
    entryComponents: [
        TypeCultureMySuffixComponent,
        TypeCultureMySuffixUpdateComponent,
        TypeCultureMySuffixDeleteDialogComponent,
        TypeCultureMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppTypeCultureMySuffixModule {}
