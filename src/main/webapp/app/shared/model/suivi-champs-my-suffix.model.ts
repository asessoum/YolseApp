import { Moment } from 'moment';
import { IClientMySuffix } from 'app/shared/model//client-my-suffix.model';

export interface ISuiviChampsMySuffix {
    id?: number;
    suiviID?: number;
    dVisit?: Moment;
    emplac?: string;
    obs?: string;
    dosImg?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    clients?: IClientMySuffix[];
}

export class SuiviChampsMySuffix implements ISuiviChampsMySuffix {
    constructor(
        public id?: number,
        public suiviID?: number,
        public dVisit?: Moment,
        public emplac?: string,
        public obs?: string,
        public dosImg?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public clients?: IClientMySuffix[]
    ) {
        this.estActif = false;
    }
}
