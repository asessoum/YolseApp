import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { UtilisateurMySuffixService } from './utilisateur-my-suffix.service';
import { UtilisateurMySuffixComponent } from './utilisateur-my-suffix.component';
import { UtilisateurMySuffixDetailComponent } from './utilisateur-my-suffix-detail.component';
import { UtilisateurMySuffixUpdateComponent } from './utilisateur-my-suffix-update.component';
import { UtilisateurMySuffixDeletePopupComponent } from './utilisateur-my-suffix-delete-dialog.component';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class UtilisateurMySuffixResolve implements Resolve<IUtilisateurMySuffix> {
    constructor(private service: UtilisateurMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((utilisateur: HttpResponse<UtilisateurMySuffix>) => utilisateur.body));
        }
        return of(new UtilisateurMySuffix());
    }
}

export const utilisateurRoute: Routes = [
    {
        path: 'utilisateur-my-suffix',
        component: UtilisateurMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utilisateurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-my-suffix/:id/view',
        component: UtilisateurMySuffixDetailComponent,
        resolve: {
            utilisateur: UtilisateurMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utilisateurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-my-suffix/new',
        component: UtilisateurMySuffixUpdateComponent,
        resolve: {
            utilisateur: UtilisateurMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utilisateurs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-my-suffix/:id/edit',
        component: UtilisateurMySuffixUpdateComponent,
        resolve: {
            utilisateur: UtilisateurMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utilisateurs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utilisateurPopupRoute: Routes = [
    {
        path: 'utilisateur-my-suffix/:id/delete',
        component: UtilisateurMySuffixDeletePopupComponent,
        resolve: {
            utilisateur: UtilisateurMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utilisateurs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
