import { Moment } from 'moment';
import { IBesoinEngraisMySuffix } from 'app/shared/model//besoin-engrais-my-suffix.model';

export interface IEngraisMySuffix {
    id?: number;
    engraisID?: number;
    libelle?: string;
    prixUnitaire?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    besoinEngrais?: IBesoinEngraisMySuffix[];
}

export class EngraisMySuffix implements IEngraisMySuffix {
    constructor(
        public id?: number,
        public engraisID?: number,
        public libelle?: string,
        public prixUnitaire?: number,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public besoinEngrais?: IBesoinEngraisMySuffix[]
    ) {
        this.estActif = false;
    }
}
