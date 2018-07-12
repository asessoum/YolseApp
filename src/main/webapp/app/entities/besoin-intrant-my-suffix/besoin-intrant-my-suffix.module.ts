import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    BesoinIntrantMySuffixComponent,
    BesoinIntrantMySuffixDetailComponent,
    BesoinIntrantMySuffixUpdateComponent,
    BesoinIntrantMySuffixDeletePopupComponent,
    BesoinIntrantMySuffixDeleteDialogComponent,
    besoinIntrantRoute,
    besoinIntrantPopupRoute
} from './';

const ENTITY_STATES = [...besoinIntrantRoute, ...besoinIntrantPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BesoinIntrantMySuffixComponent,
        BesoinIntrantMySuffixDetailComponent,
        BesoinIntrantMySuffixUpdateComponent,
        BesoinIntrantMySuffixDeleteDialogComponent,
        BesoinIntrantMySuffixDeletePopupComponent
    ],
    entryComponents: [
        BesoinIntrantMySuffixComponent,
        BesoinIntrantMySuffixUpdateComponent,
        BesoinIntrantMySuffixDeleteDialogComponent,
        BesoinIntrantMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppBesoinIntrantMySuffixModule {}
