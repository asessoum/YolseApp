import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';
import { ProfileMySuffixService } from './profile-my-suffix.service';
import { ProfileMySuffixComponent } from './profile-my-suffix.component';
import { ProfileMySuffixDetailComponent } from './profile-my-suffix-detail.component';
import { ProfileMySuffixUpdateComponent } from './profile-my-suffix-update.component';
import { ProfileMySuffixDeletePopupComponent } from './profile-my-suffix-delete-dialog.component';
import { IProfileMySuffix } from 'app/shared/model/profile-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class ProfileMySuffixResolve implements Resolve<IProfileMySuffix> {
    constructor(private service: ProfileMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((profile: HttpResponse<ProfileMySuffix>) => profile.body));
        }
        return of(new ProfileMySuffix());
    }
}

export const profileRoute: Routes = [
    {
        path: 'profile-my-suffix',
        component: ProfileMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profile-my-suffix/:id/view',
        component: ProfileMySuffixDetailComponent,
        resolve: {
            profile: ProfileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profile-my-suffix/new',
        component: ProfileMySuffixUpdateComponent,
        resolve: {
            profile: ProfileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profile-my-suffix/:id/edit',
        component: ProfileMySuffixUpdateComponent,
        resolve: {
            profile: ProfileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const profilePopupRoute: Routes = [
    {
        path: 'profile-my-suffix/:id/delete',
        component: ProfileMySuffixDeletePopupComponent,
        resolve: {
            profile: ProfileMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Profiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
