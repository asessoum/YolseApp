import { Moment } from 'moment';
import { ILangueMySuffix } from 'app/shared/model//langue-my-suffix.model';
import { IUtilisateurMySuffix } from 'app/shared/model//utilisateur-my-suffix.model';
import { ICommuneMySuffix } from 'app/shared/model//commune-my-suffix.model';

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
    langues?: ILangueMySuffix[];
    utilisateurs?: IUtilisateurMySuffix[];
    communes?: ICommuneMySuffix[];
    besoinIntrantsId?: number;
    suiviChampsId?: number;
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
        public langues?: ILangueMySuffix[],
        public utilisateurs?: IUtilisateurMySuffix[],
        public communes?: ICommuneMySuffix[],
        public besoinIntrantsId?: number,
        public suiviChampsId?: number
    ) {
        this.estMarie = false;
        this.estActif = false;
    }
}
