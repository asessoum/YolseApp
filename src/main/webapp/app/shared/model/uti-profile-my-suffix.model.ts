import { Moment } from 'moment';
import { IUtilisateurMySuffix } from 'app/shared/model//utilisateur-my-suffix.model';
import { IProfileMySuffix } from 'app/shared/model//profile-my-suffix.model';

export interface IUtiProfileMySuffix {
    id?: number;
    utiProID?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    utilisateurs?: IUtilisateurMySuffix[];
    profiles?: IProfileMySuffix[];
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
        public utilisateurs?: IUtilisateurMySuffix[],
        public profiles?: IProfileMySuffix[]
    ) {
        this.estActif = false;
    }
}
