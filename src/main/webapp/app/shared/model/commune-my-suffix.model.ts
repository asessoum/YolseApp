import { Moment } from 'moment';
import { IUtilisateurMySuffix } from 'app/shared/model/utilisateur-my-suffix.model';
import { IClientMySuffix } from 'app/shared/model/client-my-suffix.model';

export interface ICommuneMySuffix {
    id?: number;
    communeID?: number;
    nomCommune?: string;
    nomProvince?: string;
    nomRegion?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    paysId?: number;
    utilisateurs?: IUtilisateurMySuffix[];
    clients?: IClientMySuffix[];
}

export class CommuneMySuffix implements ICommuneMySuffix {
    constructor(
        public id?: number,
        public communeID?: number,
        public nomCommune?: string,
        public nomProvince?: string,
        public nomRegion?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public paysId?: number,
        public utilisateurs?: IUtilisateurMySuffix[],
        public clients?: IClientMySuffix[]
    ) {
        this.estActif = this.estActif || false;
    }
}
