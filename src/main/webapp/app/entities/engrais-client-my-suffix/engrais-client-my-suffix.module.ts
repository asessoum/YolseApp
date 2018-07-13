import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    EngraisClientMySuffixComponent,
    EngraisClientMySuffixDetailComponent,
    EngraisClientMySuffixUpdateComponent,
    EngraisClientMySuffixDeletePopupComponent,
    EngraisClientMySuffixDeleteDialogComponent,
    engraisClientRoute,
    engraisClientPopupRoute
} from './';

const ENTITY_STATES = [...engraisClientRoute, ...engraisClientPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EngraisClientMySuffixComponent,
        EngraisClientMySuffixDetailComponent,
        EngraisClientMySuffixUpdateComponent,
        EngraisClientMySuffixDeleteDialogComponent,
        EngraisClientMySuffixDeletePopupComponent
    ],
    entryComponents: [
        EngraisClientMySuffixComponent,
        EngraisClientMySuffixUpdateComponent,
        EngraisClientMySuffixDeleteDialogComponent,
        EngraisClientMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppEngraisClientMySuffixModule {}
