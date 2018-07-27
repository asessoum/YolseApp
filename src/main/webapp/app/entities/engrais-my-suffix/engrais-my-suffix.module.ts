import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    EngraisMySuffixComponent,
    EngraisMySuffixDetailComponent,
    EngraisMySuffixUpdateComponent,
    EngraisMySuffixDeletePopupComponent,
    EngraisMySuffixDeleteDialogComponent,
    engraisRoute,
    engraisPopupRoute
} from './';

const ENTITY_STATES = [...engraisRoute, ...engraisPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EngraisMySuffixComponent,
        EngraisMySuffixDetailComponent,
        EngraisMySuffixUpdateComponent,
        EngraisMySuffixDeleteDialogComponent,
        EngraisMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EngraisMySuffixComponent,
        EngraisMySuffixUpdateComponent,
        EngraisMySuffixDeleteDialogComponent,
        EngraisMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppEngraisMySuffixModule {}
