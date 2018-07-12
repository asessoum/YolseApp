import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    RegionMySuffixComponent,
    RegionMySuffixDetailComponent,
    RegionMySuffixUpdateComponent,
    RegionMySuffixDeletePopupComponent,
    RegionMySuffixDeleteDialogComponent,
    regionRoute,
    regionPopupRoute
} from './';

const ENTITY_STATES = [...regionRoute, ...regionPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RegionMySuffixComponent,
        RegionMySuffixDetailComponent,
        RegionMySuffixUpdateComponent,
        RegionMySuffixDeleteDialogComponent,
        RegionMySuffixDeletePopupComponent
    ],
    entryComponents: [
        RegionMySuffixComponent,
        RegionMySuffixUpdateComponent,
        RegionMySuffixDeleteDialogComponent,
        RegionMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppRegionMySuffixModule {}
