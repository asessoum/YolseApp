import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TransactionMySuffix } from 'app/shared/model/transaction-my-suffix.model';
import { TransactionMySuffixService } from './transaction-my-suffix.service';
import { TransactionMySuffixComponent } from './transaction-my-suffix.component';
import { TransactionMySuffixDetailComponent } from './transaction-my-suffix-detail.component';
import { TransactionMySuffixUpdateComponent } from './transaction-my-suffix-update.component';
import { TransactionMySuffixDeletePopupComponent } from './transaction-my-suffix-delete-dialog.component';
import { ITransactionMySuffix } from 'app/shared/model/transaction-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class TransactionMySuffixResolve implements Resolve<ITransactionMySuffix> {
    constructor(private service: TransactionMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITransactionMySuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TransactionMySuffix>) => response.ok),
                map((transaction: HttpResponse<TransactionMySuffix>) => transaction.body)
            );
        }
        return of(new TransactionMySuffix());
    }
}

export const transactionRoute: Routes = [
    {
        path: '',
        component: TransactionMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transactions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TransactionMySuffixDetailComponent,
        resolve: {
            transaction: TransactionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transactions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TransactionMySuffixUpdateComponent,
        resolve: {
            transaction: TransactionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transactions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TransactionMySuffixUpdateComponent,
        resolve: {
            transaction: TransactionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transactions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transactionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TransactionMySuffixDeletePopupComponent,
        resolve: {
            transaction: TransactionMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transactions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
