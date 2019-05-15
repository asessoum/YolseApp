import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    PaysMySuffixComponent,
    PaysMySuffixDetailComponent,
    PaysMySuffixUpdateComponent,
    PaysMySuffixDeletePopupComponent,
    PaysMySuffixDeleteDialogComponent,
    paysRoute,
    paysPopupRoute
} from './';

const ENTITY_STATES = [...paysRoute, ...paysPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PaysMySuffixComponent,
        PaysMySuffixDetailComponent,
        PaysMySuffixUpdateComponent,
        PaysMySuffixDeleteDialogComponent,
        PaysMySuffixDeletePopupComponent
    ],
    entryComponents: [
        PaysMySuffixComponent,
        PaysMySuffixUpdateComponent,
        PaysMySuffixDeleteDialogComponent,
        PaysMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppPaysMySuffixModule {}
