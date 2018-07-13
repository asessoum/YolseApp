import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';

type EntityResponseType = HttpResponse<IUtilisateurMySuffix>;
type EntityArrayResponseType = HttpResponse<IUtilisateurMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class UtilisateurMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/utilisateurs';

    constructor(private http: HttpClient) {}

    create(utilisateur: IUtilisateurMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(utilisateur);
        return this.http
            .post<IUtilisateurMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(utilisateur: IUtilisateurMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(utilisateur);
        return this.http
            .put<IUtilisateurMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUtilisateurMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUtilisateurMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(utilisateur: IUtilisateurMySuffix): IUtilisateurMySuffix {
        const copy: IUtilisateurMySuffix = Object.assign({}, utilisateur, {
            naissance: utilisateur.naissance != null && utilisateur.naissance.isValid() ? utilisateur.naissance.toJSON() : null,
            creeLe: utilisateur.creeLe != null && utilisateur.creeLe.isValid() ? utilisateur.creeLe.toJSON() : null,
            modifLe: utilisateur.modifLe != null && utilisateur.modifLe.isValid() ? utilisateur.modifLe.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.naissance = res.body.naissance != null ? moment(res.body.naissance) : null;
        res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
        res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((utilisateur: IUtilisateurMySuffix) => {
            utilisateur.naissance = utilisateur.naissance != null ? moment(utilisateur.naissance) : null;
            utilisateur.creeLe = utilisateur.creeLe != null ? moment(utilisateur.creeLe) : null;
            utilisateur.modifLe = utilisateur.modifLe != null ? moment(utilisateur.modifLe) : null;
        });
        return res;
    }
}