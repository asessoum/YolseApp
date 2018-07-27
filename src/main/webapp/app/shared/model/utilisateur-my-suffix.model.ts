import { Moment } from 'moment';
import { ILangueMySuffix } from 'app/shared/model//langue-my-suffix.model';
import { ICommuneMySuffix } from 'app/shared/model//commune-my-suffix.model';
import { IUtilisateurMySuffix } from 'app/shared/model//utilisateur-my-suffix.model';

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
    dateNaiss?: Moment;
    genre?: Genre;
    tel?: string;
    email?: string;
    numCarteUti?: string;
    nomPAP?: string;
    prenomPAP?: string;
    telPAP?: string;
    lienPAP?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    langues?: ILangueMySuffix[];
    communes?: ICommuneMySuffix[];
    responsables?: IUtilisateurMySuffix[];
    agentsId?: number;
    profilesId?: number;
    clientsId?: number;
    suiviChampsId?: number;
}

export class UtilisateurMySuffix implements IUtilisateurMySuffix {
    constructor(
        public id?: number,
        public userID?: number,
        public login?: string,
        public mdp?: string,
        public nom?: string,
        public prenom?: string,
        public dateNaiss?: Moment,
        public genre?: Genre,
        public tel?: string,
        public email?: string,
        public numCarteUti?: string,
        public nomPAP?: string,
        public prenomPAP?: string,
        public telPAP?: string,
        public lienPAP?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public langues?: ILangueMySuffix[],
        public communes?: ICommuneMySuffix[],
        public responsables?: IUtilisateurMySuffix[],
        public agentsId?: number,
        public profilesId?: number,
        public clientsId?: number,
        public suiviChampsId?: number
    ) {
        this.estActif = false;
    }
}
