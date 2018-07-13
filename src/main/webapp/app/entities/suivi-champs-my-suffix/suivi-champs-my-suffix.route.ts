import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';
import { SuiviChampsMySuffixService } from './suivi-champs-my-suffix.service';
import { SuiviChampsMySuffixComponent } from './suivi-champs-my-suffix.component';
import { SuiviChampsMySuffixDetailComponent } from './suivi-champs-my-suffix-detail.component';
import { SuiviChampsMySuffixUpdateComponent } from './suivi-champs-my-suffix-update.component';
import { SuiviChampsMySuffixDeletePopupComponent } from './suivi-champs-my-suffix-delete-dialog.component';
import { ISuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class SuiviChampsMySuffixResolve implements Resolve<ISuiviChampsMySuffix> {
    constructor(private service: SuiviChampsMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((suiviChamps: HttpResponse<SuiviChampsMySuffix>) => suiviChamps.body));
        }
        return of(new SuiviChampsMySuffix());
    }
}

export const suiviChampsRoute: Routes = [
    {
        path: 'suivi-champs-my-suffix',
        component: SuiviChampsMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuiviChamps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'suivi-champs-my-suffix/:id/view',
        component: SuiviChampsMySuffixDetailComponent,
        resolve: {
            suiviChamps: SuiviChampsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuiviChamps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'suivi-champs-my-suffix/new',
        component: SuiviChampsMySuffixUpdateComponent,
        resolve: {
            suiviChamps: SuiviChampsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuiviChamps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'suivi-champs-my-suffix/:id/edit',
        component: SuiviChampsMySuffixUpdateComponent,
        resolve: {
            suiviChamps: SuiviChampsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuiviChamps'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const suiviChampsPopupRoute: Routes = [
    {
        path: 'suivi-champs-my-suffix/:id/delete',
        component: SuiviChampsMySuffixDeletePopupComponent,
        resolve: {
            suiviChamps: SuiviChampsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SuiviChamps'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
