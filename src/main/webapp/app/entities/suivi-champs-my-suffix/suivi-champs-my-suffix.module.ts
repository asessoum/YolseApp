import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    SuiviChampsMySuffixComponent,
    SuiviChampsMySuffixDetailComponent,
    SuiviChampsMySuffixUpdateComponent,
    SuiviChampsMySuffixDeletePopupComponent,
    SuiviChampsMySuffixDeleteDialogComponent,
    suiviChampsRoute,
    suiviChampsPopupRoute
} from './';

const ENTITY_STATES = [...suiviChampsRoute, ...suiviChampsPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SuiviChampsMySuffixComponent,
        SuiviChampsMySuffixDetailComponent,
        SuiviChampsMySuffixUpdateComponent,
        SuiviChampsMySuffixDeleteDialogComponent,
        SuiviChampsMySuffixDeletePopupComponent
    ],
    entryComponents: [
        SuiviChampsMySuffixComponent,
        SuiviChampsMySuffixUpdateComponent,
        SuiviChampsMySuffixDeleteDialogComponent,
        SuiviChampsMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppSuiviChampsMySuffixModule {}
