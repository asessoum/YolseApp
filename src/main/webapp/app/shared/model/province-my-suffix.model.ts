import { Moment } from 'moment';
import { IRegionMySuffix } from 'app/shared/model//region-my-suffix.model';

export interface IProvinceMySuffix {
    id?: number;
    provinceID?: number;
    nomProvince?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    regions?: IRegionMySuffix[];
    communesId?: number;
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
        public regions?: IRegionMySuffix[],
        public communesId?: number
    ) {
        this.estActif = false;
    }
}
