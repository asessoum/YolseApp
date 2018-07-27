import { Moment } from 'moment';

export interface IReferenceMySuffix {
    id?: number;
    idRef?: number;
    libelleRef?: string;
    valeurRef?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
}

export class ReferenceMySuffix implements IReferenceMySuffix {
    constructor(
        public id?: number,
        public idRef?: number,
        public libelleRef?: string,
        public valeurRef?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string
    ) {
        this.estActif = false;
    }
}
