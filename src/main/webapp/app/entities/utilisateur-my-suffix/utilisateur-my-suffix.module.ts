import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    UtilisateurMySuffixComponent,
    UtilisateurMySuffixDetailComponent,
    UtilisateurMySuffixUpdateComponent,
    UtilisateurMySuffixDeletePopupComponent,
    UtilisateurMySuffixDeleteDialogComponent,
    utilisateurRoute,
    utilisateurPopupRoute
} from './';

const ENTITY_STATES = [...utilisateurRoute, ...utilisateurPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UtilisateurMySuffixComponent,
        UtilisateurMySuffixDetailComponent,
        UtilisateurMySuffixUpdateComponent,
        UtilisateurMySuffixDeleteDialogComponent,
        UtilisateurMySuffixDeletePopupComponent
    ],
    entryComponents: [
        UtilisateurMySuffixComponent,
        UtilisateurMySuffixUpdateComponent,
        UtilisateurMySuffixDeleteDialogComponent,
        UtilisateurMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppUtilisateurMySuffixModule {}
