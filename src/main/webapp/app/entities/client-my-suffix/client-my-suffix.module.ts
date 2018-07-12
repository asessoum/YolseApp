import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    ClientMySuffixComponent,
    ClientMySuffixDetailComponent,
    ClientMySuffixUpdateComponent,
    ClientMySuffixDeletePopupComponent,
    ClientMySuffixDeleteDialogComponent,
    clientRoute,
    clientPopupRoute
} from './';

const ENTITY_STATES = [...clientRoute, ...clientPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ClientMySuffixComponent,
        ClientMySuffixDetailComponent,
        ClientMySuffixUpdateComponent,
        ClientMySuffixDeleteDialogComponent,
        ClientMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ClientMySuffixComponent,
        ClientMySuffixUpdateComponent,
        ClientMySuffixDeleteDialogComponent,
        ClientMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppClientMySuffixModule {}
