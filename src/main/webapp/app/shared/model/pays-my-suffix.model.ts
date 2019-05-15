import { Moment } from 'moment';
import { ICommuneMySuffix } from 'app/shared/model/commune-my-suffix.model';

export interface IPaysMySuffix {
    id?: number;
    paysID?: number;
    nomPays?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    communes?: ICommuneMySuffix[];
}

export class PaysMySuffix implements IPaysMySuffix {
    constructor(
        public id?: number,
        public paysID?: number,
        public nomPays?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public communes?: ICommuneMySuffix[]
    ) {
        this.estActif = this.estActif || false;
    }
}
