import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    ProfilMySuffixComponent,
    ProfilMySuffixDetailComponent,
    ProfilMySuffixUpdateComponent,
    ProfilMySuffixDeletePopupComponent,
    ProfilMySuffixDeleteDialogComponent,
    profilRoute,
    profilPopupRoute
} from './';

const ENTITY_STATES = [...profilRoute, ...profilPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProfilMySuffixComponent,
        ProfilMySuffixDetailComponent,
        ProfilMySuffixUpdateComponent,
        ProfilMySuffixDeleteDialogComponent,
        ProfilMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ProfilMySuffixComponent,
        ProfilMySuffixUpdateComponent,
        ProfilMySuffixDeleteDialogComponent,
        ProfilMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppProfilMySuffixModule {}
