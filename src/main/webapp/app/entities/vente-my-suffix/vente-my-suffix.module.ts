import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    VenteMySuffixComponent,
    VenteMySuffixDetailComponent,
    VenteMySuffixUpdateComponent,
    VenteMySuffixDeletePopupComponent,
    VenteMySuffixDeleteDialogComponent,
    venteRoute,
    ventePopupRoute
} from './';

const ENTITY_STATES = [...venteRoute, ...ventePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VenteMySuffixComponent,
        VenteMySuffixDetailComponent,
        VenteMySuffixUpdateComponent,
        VenteMySuffixDeleteDialogComponent,
        VenteMySuffixDeletePopupComponent
    ],
    entryComponents: [
        VenteMySuffixComponent,
        VenteMySuffixUpdateComponent,
        VenteMySuffixDeleteDialogComponent,
        VenteMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppVenteMySuffixModule {}
