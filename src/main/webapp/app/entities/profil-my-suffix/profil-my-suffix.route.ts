import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';
import { ProfilMySuffixService } from './profil-my-suffix.service';
import { ProfilMySuffixComponent } from './profil-my-suffix.component';
import { ProfilMySuffixDetailComponent } from './profil-my-suffix-detail.component';
import { ProfilMySuffixUpdateComponent } from './profil-my-suffix-update.component';
import { ProfilMySuffixDeletePopupComponent } from './profil-my-suffix-delete-dialog.component';
import { IProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class ProfilMySuffixResolve implements Resolve<IProfilMySuffix> {
    constructor(private service: ProfilMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((profil: HttpResponse<ProfilMySuffix>) => profil.body));
        }
        return of(new ProfilMySuffix());
    }
}

export const profilRoute: Routes = [
    {
        path: 'profil-my-suffix',
        component: ProfilMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profils'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-my-suffix/:id/view',
        component: ProfilMySuffixDetailComponent,
        resolve: {
            profil: ProfilMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profils'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-my-suffix/new',
        component: ProfilMySuffixUpdateComponent,
        resolve: {
            profil: ProfilMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profils'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-my-suffix/:id/edit',
        component: ProfilMySuffixUpdateComponent,
        resolve: {
            profil: ProfilMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profils'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const profilPopupRoute: Routes = [
    {
        path: 'profil-my-suffix/:id/delete',
        component: ProfilMySuffixDeletePopupComponent,
        resolve: {
            profil: ProfilMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profils'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
