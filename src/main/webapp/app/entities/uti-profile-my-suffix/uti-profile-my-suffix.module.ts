import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    UtiProfileMySuffixComponent,
    UtiProfileMySuffixDetailComponent,
    UtiProfileMySuffixUpdateComponent,
    UtiProfileMySuffixDeletePopupComponent,
    UtiProfileMySuffixDeleteDialogComponent,
    utiProfileRoute,
    utiProfilePopupRoute
} from './';

const ENTITY_STATES = [...utiProfileRoute, ...utiProfilePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UtiProfileMySuffixComponent,
        UtiProfileMySuffixDetailComponent,
        UtiProfileMySuffixUpdateComponent,
        UtiProfileMySuffixDeleteDialogComponent,
        UtiProfileMySuffixDeletePopupComponent
    ],
    entryComponents: [
        UtiProfileMySuffixComponent,
        UtiProfileMySuffixUpdateComponent,
        UtiProfileMySuffixDeleteDialogComponent,
        UtiProfileMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppUtiProfileMySuffixModule {}
