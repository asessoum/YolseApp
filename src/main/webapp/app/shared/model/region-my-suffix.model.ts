import { Moment } from 'moment';
import { IProvinceMySuffix } from 'app/shared/model//province-my-suffix.model';

export interface IRegionMySuffix {
    id?: number;
    regionID?: number;
    nomRegion?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPpar?: string;
    provinces?: IProvinceMySuffix[];
}

export class RegionMySuffix implements IRegionMySuffix {
    constructor(
        public id?: number,
        public regionID?: number,
        public nomRegion?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPpar?: string,
        public provinces?: IProvinceMySuffix[]
    ) {
        this.estActif = false;
    }
}
