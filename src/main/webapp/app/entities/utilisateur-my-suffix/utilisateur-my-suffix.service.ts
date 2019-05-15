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
    public resourceUrl = SERVER_API_URL + 'api/utilisateurs';

    constructor(protected http: HttpClient) {}

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

    protected convertDateFromClient(utilisateur: IUtilisateurMySuffix): IUtilisateurMySuffix {
        const copy: IUtilisateurMySuffix = Object.assign({}, utilisateur, {
            dateNaiss: utilisateur.dateNaiss != null && utilisateur.dateNaiss.isValid() ? utilisateur.dateNaiss.toJSON() : null,
            dateCarteUti: utilisateur.dateCarteUti != null && utilisateur.dateCarteUti.isValid() ? utilisateur.dateCarteUti.toJSON() : null,
            creeLe: utilisateur.creeLe != null && utilisateur.creeLe.isValid() ? utilisateur.creeLe.toJSON() : null,
            modifLe: utilisateur.modifLe != null && utilisateur.modifLe.isValid() ? utilisateur.modifLe.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateNaiss = res.body.dateNaiss != null ? moment(res.body.dateNaiss) : null;
            res.body.dateCarteUti = res.body.dateCarteUti != null ? moment(res.body.dateCarteUti) : null;
            res.body.creeLe = res.body.creeLe != null ? moment(res.body.creeLe) : null;
            res.body.modifLe = res.body.modifLe != null ? moment(res.body.modifLe) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((utilisateur: IUtilisateurMySuffix) => {
                utilisateur.dateNaiss = utilisateur.dateNaiss != null ? moment(utilisateur.dateNaiss) : null;
                utilisateur.dateCarteUti = utilisateur.dateCarteUti != null ? moment(utilisateur.dateCarteUti) : null;
                utilisateur.creeLe = utilisateur.creeLe != null ? moment(utilisateur.creeLe) : null;
                utilisateur.modifLe = utilisateur.modifLe != null ? moment(utilisateur.modifLe) : null;
            });
        }
        return res;
    }
}
