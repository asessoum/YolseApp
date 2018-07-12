import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { ProvinceMySuffixService } from './province-my-suffix.service';
import { ProvinceMySuffixComponent } from './province-my-suffix.component';
import { ProvinceMySuffixDetailComponent } from './province-my-suffix-detail.component';
import { ProvinceMySuffixUpdateComponent } from './province-my-suffix-update.component';
import { ProvinceMySuffixDeletePopupComponent } from './province-my-suffix-delete-dialog.component';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class ProvinceMySuffixResolve implements Resolve<IProvinceMySuffix> {
    constructor(private service: ProvinceMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((province: HttpResponse<ProvinceMySuffix>) => province.body));
        }
        return of(new ProvinceMySuffix());
    }
}

export const provinceRoute: Routes = [
    {
        path: 'province-my-suffix',
        component: ProvinceMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Provinces'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'province-my-suffix/:id/view',
        component: ProvinceMySuffixDetailComponent,
        resolve: {
            province: ProvinceMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Provinces'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'province-my-suffix/new',
        component: ProvinceMySuffixUpdateComponent,
        resolve: {
            province: ProvinceMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Provinces'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'province-my-suffix/:id/edit',
        component: ProvinceMySuffixUpdateComponent,
        resolve: {
            province: ProvinceMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Provinces'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const provincePopupRoute: Routes = [
    {
        path: 'province-my-suffix/:id/delete',
        component: ProvinceMySuffixDeletePopupComponent,
        resolve: {
            province: ProvinceMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Provinces'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
