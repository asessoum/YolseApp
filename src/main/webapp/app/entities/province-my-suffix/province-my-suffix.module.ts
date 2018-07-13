import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    ProvinceMySuffixComponent,
    ProvinceMySuffixDetailComponent,
    ProvinceMySuffixUpdateComponent,
    ProvinceMySuffixDeletePopupComponent,
    ProvinceMySuffixDeleteDialogComponent,
    provinceRoute,
    provincePopupRoute
} from './';

const ENTITY_STATES = [...provinceRoute, ...provincePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProvinceMySuffixComponent,
        ProvinceMySuffixDetailComponent,
        ProvinceMySuffixUpdateComponent,
        ProvinceMySuffixDeleteDialogComponent,
        ProvinceMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ProvinceMySuffixComponent,
        ProvinceMySuffixUpdateComponent,
        ProvinceMySuffixDeleteDialogComponent,
        ProvinceMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppProvinceMySuffixModule {}
