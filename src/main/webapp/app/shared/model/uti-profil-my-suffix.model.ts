import { Moment } from 'moment';
import { IUtilisateurMySuffix } from 'app/shared/model//utilisateur-my-suffix.model';
import { IProfilMySuffix } from 'app/shared/model//profil-my-suffix.model';

export interface IUtiProfilMySuffix {
    id?: number;
    utiProID?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    utilisateurs?: IUtilisateurMySuffix[];
    profils?: IProfilMySuffix[];
}

export class UtiProfilMySuffix implements IUtiProfilMySuffix {
    constructor(
        public id?: number,
        public utiProID?: number,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public utilisateurs?: IUtilisateurMySuffix[],
        public profils?: IProfilMySuffix[]
    ) {
        this.estActif = false;
    }
}
