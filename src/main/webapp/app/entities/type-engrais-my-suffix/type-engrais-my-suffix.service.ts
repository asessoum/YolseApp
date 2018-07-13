import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';

type EntityResponseType = HttpResponse<ITypeEngraisMySuffix>;
type EntityArrayResponseType = HttpResponse<ITypeEngraisMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class TypeEngraisMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/type-engrais';

    constructor(private http: HttpClient) {}

    create(typeEngrais: ITypeEngraisMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(typeEngrais);
        return this.http
            .post<ITypeEngraisMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(typeEngrais: ITypeEngraisMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(typeEngrais);
        return this.http
            .put<ITypeEngraisMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITypeEngraisMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITypeEngraisMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(typeEngrais: ITypeEngraisMySuffix): ITypeEngraisMySuffix {
        const copy: ITypeEngraisMySuffix = Object.assign({}, typeEngrais, {
            creeLe: typeEngrais.creeLe != null && typeEngrais.creeLe.isValid() ? typeEngrais.creeLe.toJSON() : null,
            modifLe: typeEngrais.modifLe != null && typeEngrais.modifLe.isValid() ? typeEngrais.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((typeEngrais: ITypeEngraisMySuffix) => {
            typeEngrais.creeLe = typeEngrais.creeLe != null ? moment(typeEngrais.creeLe) : null;
            typeEngrais.modifLe = typeEngrais.modifLe != null ? moment(typeEngrais.modifLe) : null;
        });
        return res;
    }
}
