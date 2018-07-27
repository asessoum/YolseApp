import { Moment } from 'moment';

export interface ILangueMySuffix {
    id?: number;
    langueID?: number;
    libelle?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    utilisateursId?: number;
    clientsId?: number;
}

export class LangueMySuffix implements ILangueMySuffix {
    constructor(
        public id?: number,
        public langueID?: number,
        public libelle?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public utilisateursId?: number,
        public clientsId?: number
    ) {
        this.estActif = false;
    }
}
