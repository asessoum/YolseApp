import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRegionMySuffix } from 'app/shared/model/region-my-suffix.model';

type EntityResponseType = HttpResponse<IRegionMySuffix>;
type EntityArrayResponseType = HttpResponse<IRegionMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class RegionMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/regions';

    constructor(private http: HttpClient) {}

    create(region: IRegionMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(region);
        return this.http
            .post<IRegionMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(region: IRegionMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(region);
        return this.http
            .put<IRegionMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRegionMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRegionMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(region: IRegionMySuffix): IRegionMySuffix {
        const copy: IRegionMySuffix = Object.assign({}, region, {
            creeLe: region.creeLe != null && region.creeLe.isValid() ? region.creeLe.toJSON() : null,
            modifLe: region.modifLe != null && region.modifLe.isValid() ? region.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((region: IRegionMySuffix) => {
            region.creeLe = region.creeLe != null ? moment(region.creeLe) : null;
            region.modifLe = region.modifLe != null ? moment(region.modifLe) : null;
        });
        return res;
    }
}
