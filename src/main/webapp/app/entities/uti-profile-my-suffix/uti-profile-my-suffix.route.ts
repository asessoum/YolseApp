import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';
import { UtiProfileMySuffixService } from './uti-profile-my-suffix.service';
import { UtiProfileMySuffixComponent } from './uti-profile-my-suffix.component';
import { UtiProfileMySuffixDetailComponent } from './uti-profile-my-suffix-detail.component';
import { UtiProfileMySuffixUpdateComponent } from './uti-profile-my-suffix-update.component';
import { UtiProfileMySuffixDeletePopupComponent } from './uti-profile-my-suffix-delete-dialog.component';
import { IUtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class UtiProfileMySuffixResolve implements Resolve<IUtiProfileMySuffix> {
    constructor(private service: UtiProfileMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((utiProfile: HttpResponse<UtiProfileMySuffix>) => utiProfile.body));
        }
        return of(new UtiProfileMySuffix());
    }
}

export const utiProfileRoute: Routes = [
    {
        path: 'uti-profile-my-suffix',
        component: UtiProfileMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uti-profile-my-suffix/:id/view',
        component: UtiProfileMySuffixDetailComponent,
        resolve: {
            utiProfile: UtiProfileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uti-profile-my-suffix/new',
        component: UtiProfileMySuffixUpdateComponent,
        resolve: {
            utiProfile: UtiProfileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uti-profile-my-suffix/:id/edit',
        component: UtiProfileMySuffixUpdateComponent,
        resolve: {
            utiProfile: UtiProfileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utiProfilePopupRoute: Routes = [
    {
        path: 'uti-profile-my-suffix/:id/delete',
        component: UtiProfileMySuffixDeletePopupComponent,
        resolve: {
            utiProfile: UtiProfileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UtiProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
