import { Moment } from 'moment';

export interface IProfileMySuffix {
    id?: number;
    profileID?: number;
    libelle?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    utilisateursId?: number;
}

export class ProfileMySuffix implements IProfileMySuffix {
    constructor(
        public id?: number,
        public profileID?: number,
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
