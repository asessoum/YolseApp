import { Moment } from 'moment';
import { IBesoinEngraisMySuffix } from 'app/shared/model//besoin-engrais-my-suffix.model';

export interface IBesoinIntrantMySuffix {
    id?: number;
    besIntID?: number;
    superficieEsc?: number;
    qSemence?: number;
    valeurTot?: number;
    mAdhesion?: number;
    mAssur?: number;
    mGaran?: number;
    qStockGar?: number;
    magasinStock?: string;
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
    clientId?: number;
    cultureEscId?: number;
    cultureGarId?: number;
    besoinEngrais?: IBesoinEngraisMySuffix[];
}

export class BesoinIntrantMySuffix implements IBesoinIntrantMySuffix {
    constructor(
        public id?: number,
        public besIntID?: number,
        public superficieEsc?: number,
        public qSemence?: number,
        public valeurTot?: number,
        public mAdhesion?: number,
        public mAssur?: number,
        public mGaran?: number,
        public qStockGar?: number,
        public magasinStock?: string,
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
        public clientId?: number,
        public cultureEscId?: number,
        public cultureGarId?: number,
        public besoinEngrais?: IBesoinEngraisMySuffix[]
    ) {
        this.validSup = false;
        this.validRes = false;
        this.estActif = false;
    }
}
