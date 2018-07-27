import { Moment } from 'moment';
import { IUtilisateurMySuffix } from 'app/shared/model//utilisateur-my-suffix.model';
import { IClientMySuffix } from 'app/shared/model//client-my-suffix.model';
import { ISuiviChampsMySuffix } from 'app/shared/model//suivi-champs-my-suffix.model';

export interface ICommuneMySuffix {
    id?: number;
    communeID?: number;
    nomCommune?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    provinceId?: number;
    utilisateurs?: IUtilisateurMySuffix[];
    clients?: IClientMySuffix[];
    suiviChamps?: ISuiviChampsMySuffix[];
}

export class CommuneMySuffix implements ICommuneMySuffix {
    constructor(
        public id?: number,
        public communeID?: number,
        public nomCommune?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public provinceId?: number,
        public utilisateurs?: IUtilisateurMySuffix[],
        public clients?: IClientMySuffix[],
        public suiviChamps?: ISuiviChampsMySuffix[]
    ) {
        this.estActif = false;
    }
}
