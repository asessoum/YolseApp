import { Moment } from 'moment';
import { IClientMySuffix } from 'app/shared/model//client-my-suffix.model';
import { ICultureMySuffix } from 'app/shared/model//culture-my-suffix.model';

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
    clients?: IClientMySuffix[];
    cultureEscs?: ICultureMySuffix[];
    cultureGars?: ICultureMySuffix[];
    besoinEngraisId?: number;
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
        public clients?: IClientMySuffix[],
        public cultureEscs?: ICultureMySuffix[],
        public cultureGars?: ICultureMySuffix[],
        public besoinEngraisId?: number
    ) {
        this.validSup = false;
        this.validRes = false;
        this.estActif = false;
    }
}
