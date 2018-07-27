import { Moment } from 'moment';

export interface ICultureMySuffix {
    id?: number;
    engraisID?: number;
    libelle?: string;
    prixCession?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    besoinsIntrantsEscId?: number;
    besoinsIntrantsGarId?: number;
}

export class CultureMySuffix implements ICultureMySuffix {
    constructor(
        public id?: number,
        public engraisID?: number,
        public libelle?: string,
        public prixCession?: number,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public besoinsIntrantsEscId?: number,
        public besoinsIntrantsGarId?: number
    ) {
        this.estActif = false;
    }
}
