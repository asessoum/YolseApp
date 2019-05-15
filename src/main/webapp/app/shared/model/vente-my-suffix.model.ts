import { Moment } from 'moment';

export interface IVenteMySuffix {
    id?: number;
    venteID?: number;
    localID?: number;
    remoteID?: number;
    quantite?: number;
    prixVente?: number;
    margeVente?: number;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    transactionId?: number;
    articleId?: number;
}

export class VenteMySuffix implements IVenteMySuffix {
    constructor(
        public id?: number,
        public venteID?: number,
        public localID?: number,
        public remoteID?: number,
        public quantite?: number,
        public prixVente?: number,
        public margeVente?: number,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public transactionId?: number,
        public articleId?: number
    ) {}
}
