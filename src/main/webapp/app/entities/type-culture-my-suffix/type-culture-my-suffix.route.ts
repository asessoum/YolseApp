import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';
import { TypeCultureMySuffixService } from './type-culture-my-suffix.service';
import { TypeCultureMySuffixComponent } from './type-culture-my-suffix.component';
import { TypeCultureMySuffixDetailComponent } from './type-culture-my-suffix-detail.component';
import { TypeCultureMySuffixUpdateComponent } from './type-culture-my-suffix-update.component';
import { TypeCultureMySuffixDeletePopupComponent } from './type-culture-my-suffix-delete-dialog.component';
import { ITypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class TypeCultureMySuffixResolve implements Resolve<ITypeCultureMySuffix> {
    constructor(private service: TypeCultureMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((typeCulture: HttpResponse<TypeCultureMySuffix>) => typeCulture.body));
        }
        return of(new TypeCultureMySuffix());
    }
}

export const typeCultureRoute: Routes = [
    {
        path: 'type-culture-my-suffix',
        component: TypeCultureMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-culture-my-suffix/:id/view',
        component: TypeCultureMySuffixDetailComponent,
        resolve: {
            typeCulture: TypeCultureMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-culture-my-suffix/new',
        component: TypeCultureMySuffixUpdateComponent,
        resolve: {
            typeCulture: TypeCultureMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-culture-my-suffix/:id/edit',
        component: TypeCultureMySuffixUpdateComponent,
        resolve: {
            typeCulture: TypeCultureMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeCulturePopupRoute: Routes = [
    {
        path: 'type-culture-my-suffix/:id/delete',
        component: TypeCultureMySuffixDeletePopupComponent,
        resolve: {
            typeCulture: TypeCultureMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeCultures'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
