import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';

type EntityResponseType = HttpResponse<IProfilMySuffix>;
type EntityArrayResponseType = HttpResponse<IProfilMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ProfilMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/profils';

    constructor(private http: HttpClient) {}

    create(profil: IProfilMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(profil);
        return this.http
            .post<IProfilMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(profil: IProfilMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(profil);
        return this.http
            .put<IProfilMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IProfilMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IProfilMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(profil: IProfilMySuffix): IProfilMySuffix {
        const copy: IProfilMySuffix = Object.assign({}, profil, {
            creeLe: profil.creeLe != null && profil.creeLe.isValid() ? profil.creeLe.toJSON() : null,
            modifLe: profil.modifLe != null && profil.modifLe.isValid() ? profil.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((profil: IProfilMySuffix) => {
            profil.creeLe = profil.creeLe != null ? moment(profil.creeLe) : null;
            profil.modifLe = profil.modifLe != null ? moment(profil.modifLe) : null;
        });
        return res;
    }
}
