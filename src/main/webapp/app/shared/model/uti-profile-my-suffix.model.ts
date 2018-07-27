import { Moment } from 'moment';

export interface IUtiProfileMySuffix {
    id?: number;
    utiProID?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    utilisateurId?: number;
    profileId?: number;
}

export class UtiProfileMySuffix implements IUtiProfileMySuffix {
    constructor(
        public id?: number,
        public utiProID?: number,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public utilisateurId?: number,
        public profileId?: number
    ) {
        this.estActif = false;
    }
}
