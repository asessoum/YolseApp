import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';
import { EngraisClientMySuffixService } from './engrais-client-my-suffix.service';
import { EngraisClientMySuffixComponent } from './engrais-client-my-suffix.component';
import { EngraisClientMySuffixDetailComponent } from './engrais-client-my-suffix-detail.component';
import { EngraisClientMySuffixUpdateComponent } from './engrais-client-my-suffix-update.component';
import { EngraisClientMySuffixDeletePopupComponent } from './engrais-client-my-suffix-delete-dialog.component';
import { IEngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EngraisClientMySuffixResolve implements Resolve<IEngraisClientMySuffix> {
    constructor(private service: EngraisClientMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((engraisClient: HttpResponse<EngraisClientMySuffix>) => engraisClient.body));
        }
        return of(new EngraisClientMySuffix());
    }
}

export const engraisClientRoute: Routes = [
    {
        path: 'engrais-client-my-suffix',
        component: EngraisClientMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EngraisClients'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'engrais-client-my-suffix/:id/view',
        component: EngraisClientMySuffixDetailComponent,
        resolve: {
            engraisClient: EngraisClientMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EngraisClients'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'engrais-client-my-suffix/new',
        component: EngraisClientMySuffixUpdateComponent,
        resolve: {
            engraisClient: EngraisClientMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EngraisClients'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'engrais-client-my-suffix/:id/edit',
        component: EngraisClientMySuffixUpdateComponent,
        resolve: {
            engraisClient: EngraisClientMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EngraisClients'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const engraisClientPopupRoute: Routes = [
    {
        path: 'engrais-client-my-suffix/:id/delete',
        component: EngraisClientMySuffixDeletePopupComponent,
        resolve: {
            engraisClient: EngraisClientMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'EngraisClients'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
