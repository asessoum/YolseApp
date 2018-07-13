import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';

type EntityResponseType = HttpResponse<ISuiviChampsMySuffix>;
type EntityArrayResponseType = HttpResponse<ISuiviChampsMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class SuiviChampsMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/suivi-champs';

    constructor(private http: HttpClient) {}

    create(suiviChamps: ISuiviChampsMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(suiviChamps);
        return this.http
            .post<ISuiviChampsMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(suiviChamps: ISuiviChampsMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(suiviChamps);
        return this.http
            .put<ISuiviChampsMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISuiviChampsMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISuiviChampsMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(suiviChamps: ISuiviChampsMySuffix): ISuiviChampsMySuffix {
        const copy: ISuiviChampsMySuffix = Object.assign({}, suiviChamps, {
            dVisit: suiviChamps.dVisit != null && suiviChamps.dVisit.isValid() ? suiviChamps.dVisit.toJSON() : null,
            creeLe: suiviChamps.creeLe != null && suiviChamps.creeLe.isValid() ? suiviChamps.creeLe.toJSON() : null,
            modifLe: suiviChamps.modifLe != null && suiviChamps.modifLe.isValid() ? suiviChamps.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dVisit = res.body.dVisit != null ? moment(res.body.dVisit) : null;
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((suiviChamps: ISuiviChampsMySuffix) => {
            suiviChamps.dVisit = suiviChamps.dVisit != null ? moment(suiviChamps.dVisit) : null;
            suiviChamps.creeLe = suiviChamps.creeLe != null ? moment(suiviChamps.creeLe) : null;
            suiviChamps.modifLe = suiviChamps.modifLe != null ? moment(suiviChamps.modifLe) : null;
        });
        return res;
    }
}
