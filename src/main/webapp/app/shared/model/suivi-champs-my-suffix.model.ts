import { Moment } from 'moment';

export interface ISuiviChampsMySuffix {
    id?: number;
    suiviID?: number;
    dVisit?: Moment;
    emplac?: string;
    obsAgent?: string;
    obsRes?: string;
    dosImg?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    clientId?: number;
    utilisateurId?: number;
    communeId?: number;
}

export class SuiviChampsMySuffix implements ISuiviChampsMySuffix {
    constructor(
        public id?: number,
        public suiviID?: number,
        public dVisit?: Moment,
        public emplac?: string,
        public obsAgent?: string,
        public obsRes?: string,
        public dosImg?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public clientId?: number,
        public utilisateurId?: number,
        public communeId?: number
    ) {
        this.estActif = false;
    }
}
