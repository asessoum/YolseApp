import { Moment } from 'moment';

export interface IBesoinEngraisMySuffix {
    id?: number;
    qEngrais?: number;
    pTotEngr?: number;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    besoinIntrantId?: number;
    engraisId?: number;
}

export class BesoinEngraisMySuffix implements IBesoinEngraisMySuffix {
    constructor(
        public id?: number,
        public qEngrais?: number,
        public pTotEngr?: number,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public besoinIntrantId?: number,
        public engraisId?: number
    ) {}
}
