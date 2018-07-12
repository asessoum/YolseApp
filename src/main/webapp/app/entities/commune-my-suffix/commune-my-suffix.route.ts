import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';
import { CommuneMySuffixService } from './commune-my-suffix.service';
import { CommuneMySuffixComponent } from './commune-my-suffix.component';
import { CommuneMySuffixDetailComponent } from './commune-my-suffix-detail.component';
import { CommuneMySuffixUpdateComponent } from './commune-my-suffix-update.component';
import { CommuneMySuffixDeletePopupComponent } from './commune-my-suffix-delete-dialog.component';
import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class CommuneMySuffixResolve implements Resolve<ICommuneMySuffix> {
    constructor(private service: CommuneMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((commune: HttpResponse<CommuneMySuffix>) => commune.body));
        }
        return of(new CommuneMySuffix());
    }
}

export const communeRoute: Routes = [
    {
        path: 'commune-my-suffix',
        component: CommuneMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Communes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'commune-my-suffix/:id/view',
        component: CommuneMySuffixDetailComponent,
        resolve: {
            commune: CommuneMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Communes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'commune-my-suffix/new',
        component: CommuneMySuffixUpdateComponent,
        resolve: {
            commune: CommuneMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Communes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'commune-my-suffix/:id/edit',
        component: CommuneMySuffixUpdateComponent,
        resolve: {
            commune: CommuneMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Communes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const communePopupRoute: Routes = [
    {
        path: 'commune-my-suffix/:id/delete',
        component: CommuneMySuffixDeletePopupComponent,
        resolve: {
            commune: CommuneMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Communes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
