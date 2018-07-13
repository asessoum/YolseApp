import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';

type EntityResponseType = HttpResponse<IProvinceMySuffix>;
type EntityArrayResponseType = HttpResponse<IProvinceMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ProvinceMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/provinces';

    constructor(private http: HttpClient) {}

    create(province: IProvinceMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(province);
        return this.http
            .post<IProvinceMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(province: IProvinceMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(province);
        return this.http
            .put<IProvinceMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IProvinceMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IProvinceMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(province: IProvinceMySuffix): IProvinceMySuffix {
        const copy: IProvinceMySuffix = Object.assign({}, province, {
            creeLe: province.creeLe != null && province.creeLe.isValid() ? province.creeLe.toJSON() : null,
            modifLe: province.modifLe != null && province.modifLe.isValid() ? province.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((province: IProvinceMySuffix) => {
            province.creeLe = province.creeLe != null ? moment(province.creeLe) : null;
            province.modifLe = province.modifLe != null ? moment(province.modifLe) : null;
        });
        return res;
    }
}
