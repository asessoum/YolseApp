import { Moment } from 'moment';
import { IUtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';

export interface IProfileMySuffix {
    id?: number;
    profileID?: number;
    libelle?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    utilisateurs?: IUtiProfileMySuffix[];
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
        public utilisateurs?: IUtiProfileMySuffix[]
    ) {
        this.estActif = this.estActif || false;
    }
}
