import { Moment } from 'moment';
import { IVenteMySuffix } from 'app/shared/model/vente-my-suffix.model';

export interface ITransactionMySuffix {
    id?: number;
    transactionID?: number;
    localID?: number;
    remoteID?: number;
    venteID?: number;
    vendeurID?: number;
    quantiteTotal?: number;
    prixTotalTransaction?: number;
    validSup?: boolean;
    validRes?: boolean;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    clientId?: number;
    vendeurIDId?: number;
    ventes?: IVenteMySuffix[];
}

export class TransactionMySuffix implements ITransactionMySuffix {
    constructor(
        public id?: number,
        public transactionID?: number,
        public localID?: number,
        public remoteID?: number,
        public venteID?: number,
        public vendeurID?: number,
        public quantiteTotal?: number,
        public prixTotalTransaction?: number,
        public validSup?: boolean,
        public validRes?: boolean,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public clientId?: number,
        public vendeurIDId?: number,
        public ventes?: IVenteMySuffix[]
    ) {
        this.validSup = this.validSup || false;
        this.validRes = this.validRes || false;
        this.estActif = this.estActif || false;
    }
}
