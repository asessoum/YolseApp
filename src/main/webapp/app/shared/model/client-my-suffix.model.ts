import { Moment } from 'moment';
import { IBesoinIntrantMySuffix } from 'app/shared/model//besoin-intrant-my-suffix.model';
import { ISuiviChampsMySuffix } from 'app/shared/model//suivi-champs-my-suffix.model';

export const enum Genre {
    HOMME = 'HOMME',
    FEMME = 'FEMME'
}

export interface IClientMySuffix {
    id?: number;
    clientID?: string;
    nom?: string;
    prenom?: string;
    naissance?: Moment;
    genre?: Genre;
    estMarie?: boolean;
    numCarteCli?: string;
    dCarteUtil?: Moment;
    village?: string;
    tel?: string;
    email?: string;
    groupe?: string;
    photoID?: string;
    tailleMenage?: number;
    superficiePos?: number;
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
    utilisateurId?: number;
    communeId?: number;
    besoinIntrants?: IBesoinIntrantMySuffix[];
    suiviChamps?: ISuiviChampsMySuffix[];
}

export class ClientMySuffix implements IClientMySuffix {
    constructor(
        public id?: number,
        public clientID?: string,
        public nom?: string,
        public prenom?: string,
        public naissance?: Moment,
        public genre?: Genre,
        public estMarie?: boolean,
        public numCarteCli?: string,
        public dCarteUtil?: Moment,
        public village?: string,
        public tel?: string,
        public email?: string,
        public groupe?: string,
        public photoID?: string,
        public tailleMenage?: number,
        public superficiePos?: number,
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
        public utilisateurId?: number,
        public communeId?: number,
        public besoinIntrants?: IBesoinIntrantMySuffix[],
        public suiviChamps?: ISuiviChampsMySuffix[]
    ) {
        this.estMarie = false;
        this.estActif = false;
    }
}
