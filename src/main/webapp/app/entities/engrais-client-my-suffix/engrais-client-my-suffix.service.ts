import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';

type EntityResponseType = HttpResponse<IEngraisClientMySuffix>;
type EntityArrayResponseType = HttpResponse<IEngraisClientMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class EngraisClientMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/engrais-clients';

    constructor(private http: HttpClient) {}

    create(engraisClient: IEngraisClientMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(engraisClient);
        return this.http
            .post<IEngraisClientMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(engraisClient: IEngraisClientMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(engraisClient);
        return this.http
            .put<IEngraisClientMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEngraisClientMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEngraisClientMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(engraisClient: IEngraisClientMySuffix): IEngraisClientMySuffix {
        const copy: IEngraisClientMySuffix = Object.assign({}, engraisClient, {
            creeLe: engraisClient.creeLe != null && engraisClient.creeLe.isValid() ? engraisClient.creeLe.toJSON() : null,
            modifLe: engraisClient.modifLe != null && engraisClient.modifLe.isValid() ? engraisClient.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((engraisClient: IEngraisClientMySuffix) => {
            engraisClient.creeLe = engraisClient.creeLe != null ? moment(engraisClient.creeLe) : null;
            engraisClient.modifLe = engraisClient.modifLe != null ? moment(engraisClient.modifLe) : null;
        });
        return res;
    }
}
