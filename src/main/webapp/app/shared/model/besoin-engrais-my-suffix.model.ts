import { Moment } from 'moment';
import { IBesoinIntrantMySuffix } from 'app/shared/model//besoin-intrant-my-suffix.model';
import { IEngraisMySuffix } from 'app/shared/model//engrais-my-suffix.model';

export interface IBesoinEngraisMySuffix {
    id?: number;
    qEngrais?: number;
    pTotEngr?: number;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    besoinIntrants?: IBesoinIntrantMySuffix[];
    engrais?: IEngraisMySuffix[];
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
        public besoinIntrants?: IBesoinIntrantMySuffix[],
        public engrais?: IEngraisMySuffix[]
    ) {}
}
