import { Moment } from 'moment';

export interface IProfilMySuffix {
    id?: number;
    profilID?: number;
    libelle?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    utilisateursId?: number;
}

export class ProfilMySuffix implements IProfilMySuffix {
    constructor(
        public id?: number,
        public profilID?: number,
        public libelle?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public utilisateursId?: number
    ) {
        this.estActif = false;
    }
}
