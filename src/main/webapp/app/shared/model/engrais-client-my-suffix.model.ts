import { Moment } from 'moment';
import { IClientMySuffix } from 'app/shared/model//client-my-suffix.model';
import { ITypeEngraisMySuffix } from 'app/shared/model//type-engrais-my-suffix.model';

export interface IEngraisClientMySuffix {
    id?: number;
    engCliID?: number;
    qEngrais?: number;
    pTotEngr?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    clients?: IClientMySuffix[];
    typeEngrais?: ITypeEngraisMySuffix[];
    besoinIntrantsId?: number;
}

export class EngraisClientMySuffix implements IEngraisClientMySuffix {
    constructor(
        public id?: number,
        public engCliID?: number,
        public qEngrais?: number,
        public pTotEngr?: number,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public clients?: IClientMySuffix[],
        public typeEngrais?: ITypeEngraisMySuffix[],
        public besoinIntrantsId?: number
    ) {
        this.estActif = false;
    }
}
