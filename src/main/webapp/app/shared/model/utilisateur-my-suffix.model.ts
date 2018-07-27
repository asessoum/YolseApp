import { Moment } from 'moment';
import { IUtilisateurMySuffix } from 'app/shared/model//utilisateur-my-suffix.model';
import { IUtiProfileMySuffix } from 'app/shared/model//uti-profile-my-suffix.model';
import { IClientMySuffix } from 'app/shared/model//client-my-suffix.model';
import { ISuiviChampsMySuffix } from 'app/shared/model//suivi-champs-my-suffix.model';

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
    langueId?: number;
    communeId?: number;
    responsableId?: number;
    agents?: IUtilisateurMySuffix[];
    profiles?: IUtiProfileMySuffix[];
    clients?: IClientMySuffix[];
    suiviChamps?: ISuiviChampsMySuffix[];
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
        public langueId?: number,
        public communeId?: number,
        public responsableId?: number,
        public agents?: IUtilisateurMySuffix[],
        public profiles?: IUtiProfileMySuffix[],
        public clients?: IClientMySuffix[],
        public suiviChamps?: ISuiviChampsMySuffix[]
    ) {
        this.estActif = false;
    }
}
