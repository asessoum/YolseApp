import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';

type EntityResponseType = HttpResponse<IClientMySuffix>;
type EntityArrayResponseType = HttpResponse<IClientMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ClientMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/clients';

    constructor(private http: HttpClient) {}

    create(client: IClientMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(client);
        return this.http
            .post<IClientMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(client: IClientMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(client);
        return this.http
            .put<IClientMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IClientMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IClientMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(client: IClientMySuffix): IClientMySuffix {
        const copy: IClientMySuffix = Object.assign({}, client, {
            naissance: client.naissance != null && client.naissance.isValid() ? client.naissance.toJSON() : null,
            validCin: client.validCin != null && client.validCin.isValid() ? client.validCin.toJSON() : null,
            creeLe: client.creeLe != null && client.creeLe.isValid() ? client.creeLe.toJSON() : null,
            modifLe: client.modifLe != null && client.modifLe.isValid() ? client.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.naissance = res.body.naissance != null ? moment(res.body.naissance) : null;
        res.body.validCin = res.body.validCin != null ? moment(res.body.validCin) : null;
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((client: IClientMySuffix) => {
            client.naissance = client.naissance != null ? moment(client.naissance) : null;
            client.validCin = client.validCin != null ? moment(client.validCin) : null;
            client.creeLe = client.creeLe != null ? moment(client.creeLe) : null;
            client.modifLe = client.modifLe != null ? moment(client.modifLe) : null;
        });
        return res;
    }
}
