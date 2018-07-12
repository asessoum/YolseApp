import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';

type EntityResponseType = HttpResponse<IUtiProfilMySuffix>;
type EntityArrayResponseType = HttpResponse<IUtiProfilMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class UtiProfilMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/uti-profils';

    constructor(private http: HttpClient) {}

    create(utiProfil: IUtiProfilMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(utiProfil);
        return this.http
            .post<IUtiProfilMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(utiProfil: IUtiProfilMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(utiProfil);
        return this.http
            .put<IUtiProfilMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUtiProfilMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUtiProfilMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(utiProfil: IUtiProfilMySuffix): IUtiProfilMySuffix {
        const copy: IUtiProfilMySuffix = Object.assign({}, utiProfil, {
            creeLe: utiProfil.creeLe != null && utiProfil.creeLe.isValid() ? utiProfil.creeLe.toJSON() : null,
            modifLe: utiProfil.modifLe != null && utiProfil.modifLe.isValid() ? utiProfil.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((utiProfil: IUtiProfilMySuffix) => {
            utiProfil.creeLe = utiProfil.creeLe != null ? moment(utiProfil.creeLe) : null;
            utiProfil.modifLe = utiProfil.modifLe != null ? moment(utiProfil.modifLe) : null;
        });
        return res;
    }
}
