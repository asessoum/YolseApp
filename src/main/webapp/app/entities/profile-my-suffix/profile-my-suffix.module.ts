import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    ProfileMySuffixComponent,
    ProfileMySuffixDetailComponent,
    ProfileMySuffixUpdateComponent,
    ProfileMySuffixDeletePopupComponent,
    ProfileMySuffixDeleteDialogComponent,
    profileRoute,
    profilePopupRoute
} from './';

const ENTITY_STATES = [...profileRoute, ...profilePopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProfileMySuffixComponent,
        ProfileMySuffixDetailComponent,
        ProfileMySuffixUpdateComponent,
        ProfileMySuffixDeleteDialogComponent,
        ProfileMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ProfileMySuffixComponent,
        ProfileMySuffixUpdateComponent,
        ProfileMySuffixDeleteDialogComponent,
        ProfileMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppProfileMySuffixModule {}
