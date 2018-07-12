import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    CommuneMySuffixComponent,
    CommuneMySuffixDetailComponent,
    CommuneMySuffixUpdateComponent,
    CommuneMySuffixDeletePopupComponent,
    CommuneMySuffixDeleteDialogComponent,
    communeRoute,
    communePopupRoute
} from './';

const ENTITY_STATES = [...communeRoute, ...communePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CommuneMySuffixComponent,
        CommuneMySuffixDetailComponent,
        CommuneMySuffixUpdateComponent,
        CommuneMySuffixDeleteDialogComponent,
        CommuneMySuffixDeletePopupComponent
    ],
    entryComponents: [
        CommuneMySuffixComponent,
        CommuneMySuffixUpdateComponent,
        CommuneMySuffixDeleteDialogComponent,
        CommuneMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppCommuneMySuffixModule {}
