import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LangueMySuffix } from 'app/shared/model/langue-my-suffix.model';
import { LangueMySuffixService } from './langue-my-suffix.service';
import { LangueMySuffixComponent } from './langue-my-suffix.component';
import { LangueMySuffixDetailComponent } from './langue-my-suffix-detail.component';
import { LangueMySuffixUpdateComponent } from './langue-my-suffix-update.component';
import { LangueMySuffixDeletePopupComponent } from './langue-my-suffix-delete-dialog.component';
import { ILangueMySuffix } from 'app/shared/model/langue-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class LangueMySuffixResolve implements Resolve<ILangueMySuffix> {
    constructor(private service: LangueMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((langue: HttpResponse<LangueMySuffix>) => langue.body));
        }
        return of(new LangueMySuffix());
    }
}

export const langueRoute: Routes = [
    {
        path: 'langue-my-suffix',
        component: LangueMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Langues'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'langue-my-suffix/:id/view',
        component: LangueMySuffixDetailComponent,
        resolve: {
            langue: LangueMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Langues'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'langue-my-suffix/new',
        component: LangueMySuffixUpdateComponent,
        resolve: {
            langue: LangueMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Langues'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'langue-my-suffix/:id/edit',
        component: LangueMySuffixUpdateComponent,
        resolve: {
            langue: LangueMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Langues'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const languePopupRoute: Routes = [
    {
        path: 'langue-my-suffix/:id/delete',
        component: LangueMySuffixDeletePopupComponent,
        resolve: {
            langue: LangueMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Langues'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
