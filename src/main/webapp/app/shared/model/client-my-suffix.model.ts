import { Moment } from 'moment';
import { ITransactionMySuffix } from 'app/shared/model/transaction-my-suffix.model';

export const enum Genre {
    HOMME = 'HOMME',
    FEMME = 'FEMME'
}

export interface IClientMySuffix {
    id?: number;
    clientID?: string;
    localID?: number;
    remoteID?: number;
    nom?: string;
    prenom?: string;
    naissance?: Moment;
    genre?: Genre;
    numCarteCli?: string;
    dCarteUtil?: Moment;
    tel?: string;
    email?: string;
    photoID?: string;
    infoSupplementaires?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    langueId?: number;
    commercialId?: number;
    communeId?: number;
    transactions?: ITransactionMySuffix[];
}

export class ClientMySuffix implements IClientMySuffix {
    constructor(
        public id?: number,
        public clientID?: string,
        public localID?: number,
        public remoteID?: number,
        public nom?: string,
        public prenom?: string,
        public naissance?: Moment,
        public genre?: Genre,
        public numCarteCli?: string,
        public dCarteUtil?: Moment,
        public tel?: string,
        public email?: string,
        public photoID?: string,
        public infoSupplementaires?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public langueId?: number,
        public commercialId?: number,
        public communeId?: number,
        public transactions?: ITransactionMySuffix[]
    ) {
        this.estActif = this.estActif || false;
    }
}
