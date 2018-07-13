import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';
import { UtiProfilMySuffixService } from './uti-profil-my-suffix.service';
import { UtiProfilMySuffixComponent } from './uti-profil-my-suffix.component';
import { UtiProfilMySuffixDetailComponent } from './uti-profil-my-suffix-detail.component';
import { UtiProfilMySuffixUpdateComponent } from './uti-profil-my-suffix-update.component';
import { UtiProfilMySuffixDeletePopupComponent } from './uti-profil-my-suffix-delete-dialog.component';
import { IUtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class UtiProfilMySuffixResolve implements Resolve<IUtiProfilMySuffix> {
    constructor(private service: UtiProfilMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((utiProfil: HttpResponse<UtiProfilMySuffix>) => utiProfil.body));
        }
        return of(new UtiProfilMySuffix());
    }
}

export const utiProfilRoute: Routes = [
    {
        path: 'uti-profil-my-suffix',
        component: UtiProfilMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfils'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uti-profil-my-suffix/:id/view',
        component: UtiProfilMySuffixDetailComponent,
        resolve: {
            utiProfil: UtiProfilMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfils'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uti-profil-my-suffix/new',
        component: UtiProfilMySuffixUpdateComponent,
        resolve: {
            utiProfil: UtiProfilMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfils'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uti-profil-my-suffix/:id/edit',
        component: UtiProfilMySuffixUpdateComponent,
        resolve: {
            utiProfil: UtiProfilMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfils'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utiProfilPopupRoute: Routes = [
    {
        path: 'uti-profil-my-suffix/:id/delete',
        component: UtiProfilMySuffixDeletePopupComponent,
        resolve: {
            utiProfil: UtiProfilMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfils'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
