import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CultureMySuffix } from 'app/shared/model/culture-my-suffix.model';
import { CultureMySuffixService } from './culture-my-suffix.service';
import { CultureMySuffixComponent } from './culture-my-suffix.component';
import { CultureMySuffixDetailComponent } from './culture-my-suffix-detail.component';
import { CultureMySuffixUpdateComponent } from './culture-my-suffix-update.component';
import { CultureMySuffixDeletePopupComponent } from './culture-my-suffix-delete-dialog.component';
import { ICultureMySuffix } from 'app/shared/model/culture-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class CultureMySuffixResolve implements Resolve<ICultureMySuffix> {
    constructor(private service: CultureMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((culture: HttpResponse<CultureMySuffix>) => culture.body));
        }
        return of(new CultureMySuffix());
    }
}

export const cultureRoute: Routes = [
    {
        path: 'culture-my-suffix',
        component: CultureMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'culture-my-suffix/:id/view',
        component: CultureMySuffixDetailComponent,
        resolve: {
            culture: CultureMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'culture-my-suffix/new',
        component: CultureMySuffixUpdateComponent,
        resolve: {
            culture: CultureMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'culture-my-suffix/:id/edit',
        component: CultureMySuffixUpdateComponent,
        resolve: {
            culture: CultureMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cultures'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const culturePopupRoute: Routes = [
    {
        path: 'culture-my-suffix/:id/delete',
        component: CultureMySuffixDeletePopupComponent,
        resolve: {
            culture: CultureMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cultures'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
