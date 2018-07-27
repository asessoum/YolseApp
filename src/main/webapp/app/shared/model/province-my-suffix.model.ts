import { Moment } from 'moment';
import { ICommuneMySuffix } from 'app/shared/model//commune-my-suffix.model';

export interface IProvinceMySuffix {
    id?: number;
    provinceID?: number;
    nomProvince?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    regionId?: number;
    communes?: ICommuneMySuffix[];
}

export class ProvinceMySuffix implements IProvinceMySuffix {
    constructor(
        public id?: number,
        public provinceID?: number,
        public nomProvince?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public regionId?: number,
        public communes?: ICommuneMySuffix[]
    ) {
        this.estActif = false;
    }
}
