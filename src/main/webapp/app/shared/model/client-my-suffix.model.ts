import { Moment } from 'moment';

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
    numCin?: number;
    validCin?: Moment;
    village?: string;
    tel1?: number;
    tel2?: number;
    email?: string;
    orgaProd?: string;
    photoID?: string;
    tMenage?: number;
    nomPerAPr?: string;
    prenomPerAPr?: string;
    telPerAPr?: number;
    superfice?: number;
    qSemence?: number;
    mSimm?: number;
    valTotal?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    suiviChampsId?: number;
    engraisClientsId?: number;
    utilisateursId?: number;
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
        public numCin?: number,
        public validCin?: Moment,
        public village?: string,
        public tel1?: number,
        public tel2?: number,
        public email?: string,
        public orgaProd?: string,
        public photoID?: string,
        public tMenage?: number,
        public nomPerAPr?: string,
        public prenomPerAPr?: string,
        public telPerAPr?: number,
        public superfice?: number,
        public qSemence?: number,
        public mSimm?: number,
        public valTotal?: number,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public suiviChampsId?: number,
        public engraisClientsId?: number,
        public utilisateursId?: number
    ) {
        this.estMarie = false;
        this.estActif = false;
    }
}
