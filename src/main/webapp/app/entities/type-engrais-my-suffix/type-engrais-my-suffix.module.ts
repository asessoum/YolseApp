import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { YolseAppSharedModule } from 'app/shared';
import {
    TypeEngraisMySuffixComponent,
    TypeEngraisMySuffixDetailComponent,
    TypeEngraisMySuffixUpdateComponent,
    TypeEngraisMySuffixDeletePopupComponent,
    TypeEngraisMySuffixDeleteDialogComponent,
    typeEngraisRoute,
    typeEngraisPopupRoute
} from './';

const ENTITY_STATES = [...typeEngraisRoute, ...typeEngraisPopupRoute];

@NgModule({
    imports: [YolseAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeEngraisMySuffixComponent,
        TypeEngraisMySuffixDetailComponent,
        TypeEngraisMySuffixUpdateComponent,
        TypeEngraisMySuffixDeleteDialogComponent,
        TypeEngraisMySuffixDeletePopupComponent
    ],
    entryComponents: [
        TypeEngraisMySuffixComponent,
        TypeEngraisMySuffixUpdateComponent,
        TypeEngraisMySuffixDeleteDialogComponent,
        TypeEngraisMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppTypeEngraisMySuffixModule {}
