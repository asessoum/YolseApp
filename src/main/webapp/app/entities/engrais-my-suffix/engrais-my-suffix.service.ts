import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';

type EntityResponseType = HttpResponse<IEngraisMySuffix>;
type EntityArrayResponseType = HttpResponse<IEngraisMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class EngraisMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/engrais';

    constructor(private http: HttpClient) {}

    create(engrais: IEngraisMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(engrais);
        return this.http
            .post<IEngraisMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(engrais: IEngraisMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(engrais);
        return this.http
            .put<IEngraisMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEngraisMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEngraisMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(engrais: IEngraisMySuffix): IEngraisMySuffix {
        const copy: IEngraisMySuffix = Object.assign({}, engrais, {
            creeLe: engrais.creeLe != null && engrais.creeLe.isValid() ? engrais.creeLe.toJSON() : null,
            modifLe: engrais.modifLe != null && engrais.modifLe.isValid() ? engrais.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((engrais: IEngraisMySuffix) => {
            engrais.creeLe = engrais.creeLe != null ? moment(engrais.creeLe) : null;
            engrais.modifLe = engrais.modifLe != null ? moment(engrais.modifLe) : null;
        });
        return res;
    }
}
