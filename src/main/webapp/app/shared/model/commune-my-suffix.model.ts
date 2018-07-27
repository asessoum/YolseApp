import { Moment } from 'moment';
import { IProvinceMySuffix } from 'app/shared/model//province-my-suffix.model';

export interface ICommuneMySuffix {
    id?: number;
    communeID?: number;
    nomCommune?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    provinces?: IProvinceMySuffix[];
    utilisateursId?: number;
    clientsId?: number;
    suiviChampsId?: number;
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
        public provinces?: IProvinceMySuffix[],
        public utilisateursId?: number,
        public clientsId?: number,
        public suiviChampsId?: number
    ) {
        this.estActif = false;
    }
}
