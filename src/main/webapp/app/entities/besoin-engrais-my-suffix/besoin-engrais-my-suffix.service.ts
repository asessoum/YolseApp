import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBesoinEngraisMySuffix } from 'app/shared/model/besoin-engrais-my-suffix.model';

type EntityResponseType = HttpResponse<IBesoinEngraisMySuffix>;
type EntityArrayResponseType = HttpResponse<IBesoinEngraisMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class BesoinEngraisMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/besoin-engrais';

    constructor(private http: HttpClient) {}

    create(besoinEngrais: IBesoinEngraisMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(besoinEngrais);
        return this.http
            .post<IBesoinEngraisMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(besoinEngrais: IBesoinEngraisMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(besoinEngrais);
        return this.http
            .put<IBesoinEngraisMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBesoinEngraisMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBesoinEngraisMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(besoinEngrais: IBesoinEngraisMySuffix): IBesoinEngraisMySuffix {
        const copy: IBesoinEngraisMySuffix = Object.assign({}, besoinEngrais, {
            creeLe: besoinEngrais.creeLe != null && besoinEngrais.creeLe.isValid() ? besoinEngrais.creeLe.toJSON() : null,
            modifLe: besoinEngrais.modifLe != null && besoinEngrais.modifLe.isValid() ? besoinEngrais.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((besoinEngrais: IBesoinEngraisMySuffix) => {
            besoinEngrais.creeLe = besoinEngrais.creeLe != null ? moment(besoinEngrais.creeLe) : null;
            besoinEngrais.modifLe = besoinEngrais.modifLe != null ? moment(besoinEngrais.modifLe) : null;
        });
        return res;
    }
}
