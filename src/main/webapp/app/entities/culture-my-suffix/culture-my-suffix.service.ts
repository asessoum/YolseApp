import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICultureMySuffix } from 'app/shared/model/culture-my-suffix.model';

type EntityResponseType = HttpResponse<ICultureMySuffix>;
type EntityArrayResponseType = HttpResponse<ICultureMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class CultureMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/cultures';

    constructor(private http: HttpClient) {}

    create(culture: ICultureMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(culture);
        return this.http
            .post<ICultureMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(culture: ICultureMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(culture);
        return this.http
            .put<ICultureMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICultureMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICultureMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(culture: ICultureMySuffix): ICultureMySuffix {
        const copy: ICultureMySuffix = Object.assign({}, culture, {
            creeLe: culture.creeLe != null && culture.creeLe.isValid() ? culture.creeLe.toJSON() : null,
            modifLe: culture.modifLe != null && culture.modifLe.isValid() ? culture.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((culture: ICultureMySuffix) => {
            culture.creeLe = culture.creeLe != null ? moment(culture.creeLe) : null;
            culture.modifLe = culture.modifLe != null ? moment(culture.modifLe) : null;
        });
        return res;
    }
}
