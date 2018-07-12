import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';
import { BesoinIntrantMySuffixService } from './besoin-intrant-my-suffix.service';
import { BesoinIntrantMySuffixComponent } from './besoin-intrant-my-suffix.component';
import { BesoinIntrantMySuffixDetailComponent } from './besoin-intrant-my-suffix-detail.component';
import { BesoinIntrantMySuffixUpdateComponent } from './besoin-intrant-my-suffix-update.component';
import { BesoinIntrantMySuffixDeletePopupComponent } from './besoin-intrant-my-suffix-delete-dialog.component';
import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class BesoinIntrantMySuffixResolve implements Resolve<IBesoinIntrantMySuffix> {
    constructor(private service: BesoinIntrantMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((besoinIntrant: HttpResponse<BesoinIntrantMySuffix>) => besoinIntrant.body));
        }
        return of(new BesoinIntrantMySuffix());
    }
}

export const besoinIntrantRoute: Routes = [
    {
        path: 'besoin-intrant-my-suffix',
        component: BesoinIntrantMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinIntrants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'besoin-intrant-my-suffix/:id/view',
        component: BesoinIntrantMySuffixDetailComponent,
        resolve: {
            besoinIntrant: BesoinIntrantMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinIntrants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'besoin-intrant-my-suffix/new',
        component: BesoinIntrantMySuffixUpdateComponent,
        resolve: {
            besoinIntrant: BesoinIntrantMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinIntrants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'besoin-intrant-my-suffix/:id/edit',
        component: BesoinIntrantMySuffixUpdateComponent,
        resolve: {
            besoinIntrant: BesoinIntrantMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinIntrants'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const besoinIntrantPopupRoute: Routes = [
    {
        path: 'besoin-intrant-my-suffix/:id/delete',
        component: BesoinIntrantMySuffixDeletePopupComponent,
        resolve: {
            besoinIntrant: BesoinIntrantMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BesoinIntrants'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
