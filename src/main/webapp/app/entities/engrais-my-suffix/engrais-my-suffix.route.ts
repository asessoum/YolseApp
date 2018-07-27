import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';
import { EngraisMySuffixService } from './engrais-my-suffix.service';
import { EngraisMySuffixComponent } from './engrais-my-suffix.component';
import { EngraisMySuffixDetailComponent } from './engrais-my-suffix-detail.component';
import { EngraisMySuffixUpdateComponent } from './engrais-my-suffix-update.component';
import { EngraisMySuffixDeletePopupComponent } from './engrais-my-suffix-delete-dialog.component';
import { IEngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EngraisMySuffixResolve implements Resolve<IEngraisMySuffix> {
    constructor(private service: EngraisMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((engrais: HttpResponse<EngraisMySuffix>) => engrais.body));
        }
        return of(new EngraisMySuffix());
    }
}

export const engraisRoute: Routes = [
    {
        path: 'engrais-my-suffix',
        component: EngraisMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Engrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'engrais-my-suffix/:id/view',
        component: EngraisMySuffixDetailComponent,
        resolve: {
            engrais: EngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Engrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'engrais-my-suffix/new',
        component: EngraisMySuffixUpdateComponent,
        resolve: {
            engrais: EngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Engrais'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'engrais-my-suffix/:id/edit',
        component: EngraisMySuffixUpdateComponent,
        resolve: {
            engrais: EngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Engrais'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const engraisPopupRoute: Routes = [
    {
        path: 'engrais-my-suffix/:id/delete',
        component: EngraisMySuffixDeletePopupComponent,
        resolve: {
            engrais: EngraisMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Engrais'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
