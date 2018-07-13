import { Moment } from 'moment';

export interface IRegionMySuffix {
    id?: number;
    regionID?: number;
    nomRegion?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPpar?: string;
    provincesId?: number;
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
        public provincesId?: number
    ) {
        this.estActif = false;
    }
}
