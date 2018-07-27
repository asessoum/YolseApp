import { Moment } from 'moment';
import { IClientMySuffix } from 'app/shared/model//client-my-suffix.model';
import { IUtilisateurMySuffix } from 'app/shared/model//utilisateur-my-suffix.model';
import { ICommuneMySuffix } from 'app/shared/model//commune-my-suffix.model';

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
    clients?: IClientMySuffix[];
    utilisateurs?: IUtilisateurMySuffix[];
    communes?: ICommuneMySuffix[];
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
        public clients?: IClientMySuffix[],
        public utilisateurs?: IUtilisateurMySuffix[],
        public communes?: ICommuneMySuffix[]
    ) {
        this.estActif = false;
    }
}
