import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';

type EntityResponseType = HttpResponse<ITypeCultureMySuffix>;
type EntityArrayResponseType = HttpResponse<ITypeCultureMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class TypeCultureMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/type-cultures';

    constructor(private http: HttpClient) {}

    create(typeCulture: ITypeCultureMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(typeCulture);
        return this.http
            .post<ITypeCultureMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(typeCulture: ITypeCultureMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(typeCulture);
        return this.http
            .put<ITypeCultureMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITypeCultureMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITypeCultureMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(typeCulture: ITypeCultureMySuffix): ITypeCultureMySuffix {
        const copy: ITypeCultureMySuffix = Object.assign({}, typeCulture, {
            creeLe: typeCulture.creeLe != null && typeCulture.creeLe.isValid() ? typeCulture.creeLe.toJSON() : null,
            modifLe: typeCulture.modifLe != null && typeCulture.modifLe.isValid() ? typeCulture.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((typeCulture: ITypeCultureMySuffix) => {
            typeCulture.creeLe = typeCulture.creeLe != null ? moment(typeCulture.creeLe) : null;
            typeCulture.modifLe = typeCulture.modifLe != null ? moment(typeCulture.modifLe) : null;
        });
        return res;
    }
}
