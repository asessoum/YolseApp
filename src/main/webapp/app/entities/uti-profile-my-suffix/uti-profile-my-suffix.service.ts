import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';

type EntityResponseType = HttpResponse<IUtiProfileMySuffix>;
type EntityArrayResponseType = HttpResponse<IUtiProfileMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class UtiProfileMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/uti-profiles';

    constructor(private http: HttpClient) {}

    create(utiProfile: IUtiProfileMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(utiProfile);
        return this.http
            .post<IUtiProfileMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(utiProfile: IUtiProfileMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(utiProfile);
        return this.http
            .put<IUtiProfileMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUtiProfileMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUtiProfileMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(utiProfile: IUtiProfileMySuffix): IUtiProfileMySuffix {
        const copy: IUtiProfileMySuffix = Object.assign({}, utiProfile, {
            creeLe: utiProfile.creeLe != null && utiProfile.creeLe.isValid() ? utiProfile.creeLe.toJSON() : null,
            modifLe: utiProfile.modifLe != null && utiProfile.modifLe.isValid() ? utiProfile.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((utiProfile: IUtiProfileMySuffix) => {
            utiProfile.creeLe = utiProfile.creeLe != null ? moment(utiProfile.creeLe) : null;
            utiProfile.modifLe = utiProfile.modifLe != null ? moment(utiProfile.modifLe) : null;
        });
        return res;
    }
}
