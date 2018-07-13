import { Moment } from 'moment';

export interface ITypeCultureMySuffix {
    id?: number;
    engraisID?: number;
    libelle?: string;
    prixCession?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    engraisClientsId?: number;
    utilisateursId?: number;
}

export class TypeCultureMySuffix implements ITypeCultureMySuffix {
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
        public engraisClientsId?: number,
        public utilisateursId?: number
    ) {
        this.estActif = false;
    }
}
