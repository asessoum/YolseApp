import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BesoinEngraisMySuffix } from 'app/shared/model/besoin-engrais-my-suffix.model';
import { BesoinEngraisMySuffixService } from './besoin-engrais-my-suffix.service';
import { BesoinEngraisMySuffixComponent } from './besoin-engrais-my-suffix.component';
import { BesoinEngraisMySuffixDetailComponent } from './besoin-engrais-my-suffix-detail.component';
import { BesoinEngraisMySuffixUpdateComponent } from './besoin-engrais-my-suffix-update.component';
import { BesoinEngraisMySuffixDeletePopupComponent } from './besoin-engrais-my-suffix-delete-dialog.component';
import { IBesoinEngraisMySuffix } from 'app/shared/model/besoin-engrais-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class BesoinEngraisMySuffixResolve implements Resolve<IBesoinEngraisMySuffix> {
    constructor(private service: BesoinEngraisMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((besoinEngrais: HttpResponse<BesoinEngraisMySuffix>) => besoinEngrais.body));
        }
        return of(new BesoinEngraisMySuffix());
    }
}

export const besoinEngraisRoute: Routes = [
    {
        path: 'besoin-engrais-my-suffix',
        component: BesoinEngraisMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinEngrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'besoin-engrais-my-suffix/:id/view',
        component: BesoinEngraisMySuffixDetailComponent,
        resolve: {
            besoinEngrais: BesoinEngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinEngrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'besoin-engrais-my-suffix/new',
        component: BesoinEngraisMySuffixUpdateComponent,
        resolve: {
            besoinEngrais: BesoinEngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinEngrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'besoin-engrais-my-suffix/:id/edit',
        component: BesoinEngraisMySuffixUpdateComponent,
        resolve: {
            besoinEngrais: BesoinEngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinEngrais'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const besoinEngraisPopupRoute: Routes = [
    {
        path: 'besoin-engrais-my-suffix/:id/delete',
        component: BesoinEngraisMySuffixDeletePopupComponent,
        resolve: {
            besoinEngrais: BesoinEngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinEngrais'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
