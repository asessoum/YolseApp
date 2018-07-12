import { Moment } from 'moment';
import { IEngraisClientMySuffix } from 'app/shared/model//engrais-client-my-suffix.model';
import { ITypeCultureMySuffix } from 'app/shared/model//type-culture-my-suffix.model';

export interface IBesoinIntrantMySuffix {
    id?: number;
    besIntID?: number;
    mAdhesion?: number;
    mAssur?: number;
    mGaran?: number;
    stockGar?: number;
    magasin?: string;
    sfd?: string;
    mUniGes?: number;
    mAdmin?: number;
    mExploi?: number;
    validSup?: boolean;
    validRes?: boolean;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    engraisClients?: IEngraisClientMySuffix[];
    typeCultures?: ITypeCultureMySuffix[];
}

export class BesoinIntrantMySuffix implements IBesoinIntrantMySuffix {
    constructor(
        public id?: number,
        public besIntID?: number,
        public mAdhesion?: number,
        public mAssur?: number,
        public mGaran?: number,
        public stockGar?: number,
        public magasin?: string,
        public sfd?: string,
        public mUniGes?: number,
        public mAdmin?: number,
        public mExploi?: number,
        public validSup?: boolean,
        public validRes?: boolean,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public engraisClients?: IEngraisClientMySuffix[],
        public typeCultures?: ITypeCultureMySuffix[]
    ) {
        this.validSup = false;
        this.validRes = false;
        this.estActif = false;
    }
}
