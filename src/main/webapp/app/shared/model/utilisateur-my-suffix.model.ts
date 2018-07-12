import { Moment } from 'moment';
import { ITypeCultureMySuffix } from 'app/shared/model//type-culture-my-suffix.model';
import { IClientMySuffix } from 'app/shared/model//client-my-suffix.model';
import { ILangueMySuffix } from 'app/shared/model//langue-my-suffix.model';
import { ICommuneMySuffix } from 'app/shared/model//commune-my-suffix.model';

export const enum Genre {
    HOMME = 'HOMME',
    FEMME = 'FEMME'
}

export interface IUtilisateurMySuffix {
    id?: number;
    userID?: number;
    login?: string;
    mdp?: string;
    nom?: string;
    prenom?: string;
    naissance?: Moment;
    genre?: Genre;
    tel1?: number;
    tel2?: number;
    email?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    typeCultures?: ITypeCultureMySuffix[];
    clients?: IClientMySuffix[];
    langues?: ILangueMySuffix[];
    communes?: ICommuneMySuffix[];
    profilsId?: number;
}

export class UtilisateurMySuffix implements IUtilisateurMySuffix {
    constructor(
        public id?: number,
        public userID?: number,
        public login?: string,
        public mdp?: string,
        public nom?: string,
        public prenom?: string,
        public naissance?: Moment,
        public genre?: Genre,
        public tel1?: number,
        public tel2?: number,
        public email?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public typeCultures?: ITypeCultureMySuffix[],
        public clients?: IClientMySuffix[],
        public langues?: ILangueMySuffix[],
        public communes?: ICommuneMySuffix[],
        public profilsId?: number
    ) {
        this.estActif = false;
    }
}
