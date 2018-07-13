import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';
import { TypeEngraisMySuffixService } from './type-engrais-my-suffix.service';
import { TypeEngraisMySuffixComponent } from './type-engrais-my-suffix.component';
import { TypeEngraisMySuffixDetailComponent } from './type-engrais-my-suffix-detail.component';
import { TypeEngraisMySuffixUpdateComponent } from './type-engrais-my-suffix-update.component';
import { TypeEngraisMySuffixDeletePopupComponent } from './type-engrais-my-suffix-delete-dialog.component';
import { ITypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class TypeEngraisMySuffixResolve implements Resolve<ITypeEngraisMySuffix> {
    constructor(private service: TypeEngraisMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((typeEngrais: HttpResponse<TypeEngraisMySuffix>) => typeEngrais.body));
        }
        return of(new TypeEngraisMySuffix());
    }
}

export const typeEngraisRoute: Routes = [
    {
        path: 'type-engrais-my-suffix',
        component: TypeEngraisMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeEngrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-engrais-my-suffix/:id/view',
        component: TypeEngraisMySuffixDetailComponent,
        resolve: {
            typeEngrais: TypeEngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeEngrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-engrais-my-suffix/new',
        component: TypeEngraisMySuffixUpdateComponent,
        resolve: {
            typeEngrais: TypeEngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeEngrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-engrais-my-suffix/:id/edit',
        component: TypeEngraisMySuffixUpdateComponent,
        resolve: {
            typeEngrais: TypeEngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeEngrais'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeEngraisPopupRoute: Routes = [
    {
        path: 'type-engrais-my-suffix/:id/delete',
        component: TypeEngraisMySuffixDeletePopupComponent,
        resolve: {
            typeEngrais: TypeEngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TypeEngrais'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
