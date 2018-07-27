import { Moment } from 'moment';
import { IUtilisateurMySuffix } from 'app/shared/model//utilisateur-my-suffix.model';
import { IClientMySuffix } from 'app/shared/model//client-my-suffix.model';

export interface ILangueMySuffix {
    id?: number;
    langueID?: number;
    libelle?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    utilisateurs?: IUtilisateurMySuffix[];
    clients?: IClientMySuffix[];
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
        public utilisateurs?: IUtilisateurMySuffix[],
        public clients?: IClientMySuffix[]
    ) {
        this.estActif = false;
    }
}
