import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    UtiProfilMySuffixComponent,
    UtiProfilMySuffixDetailComponent,
    UtiProfilMySuffixUpdateComponent,
    UtiProfilMySuffixDeletePopupComponent,
    UtiProfilMySuffixDeleteDialogComponent,
    utiProfilRoute,
    utiProfilPopupRoute
} from './';

const ENTITY_STATES = [...utiProfilRoute, ...utiProfilPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UtiProfilMySuffixComponent,
        UtiProfilMySuffixDetailComponent,
        UtiProfilMySuffixUpdateComponent,
        UtiProfilMySuffixDeleteDialogComponent,
        UtiProfilMySuffixDeletePopupComponent
    ],
    entryComponents: [
        UtiProfilMySuffixComponent,
        UtiProfilMySuffixUpdateComponent,
        UtiProfilMySuffixDeleteDialogComponent,
        UtiProfilMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppUtiProfilMySuffixModule {}
