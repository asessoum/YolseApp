import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    BesoinEngraisMySuffixComponent,
    BesoinEngraisMySuffixDetailComponent,
    BesoinEngraisMySuffixUpdateComponent,
    BesoinEngraisMySuffixDeletePopupComponent,
    BesoinEngraisMySuffixDeleteDialogComponent,
    besoinEngraisRoute,
    besoinEngraisPopupRoute
} from './';

const ENTITY_STATES = [...besoinEngraisRoute, ...besoinEngraisPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BesoinEngraisMySuffixComponent,
        BesoinEngraisMySuffixDetailComponent,
        BesoinEngraisMySuffixUpdateComponent,
        BesoinEngraisMySuffixDeleteDialogComponent,
        BesoinEngraisMySuffixDeletePopupComponent
    ],
    entryComponents: [
        BesoinEngraisMySuffixComponent,
        BesoinEngraisMySuffixUpdateComponent,
        BesoinEngraisMySuffixDeleteDialogComponent,
        BesoinEngraisMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppBesoinEngraisMySuffixModule {}
