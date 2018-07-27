import { Moment } from 'moment';
import { IBesoinIntrantMySuffix } from 'app/shared/model//besoin-intrant-my-suffix.model';

export interface ICultureMySuffix {
    id?: number;
    engraisID?: number;
    libelle?: string;
    prixCession?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    besoinsIntrantsEscs?: IBesoinIntrantMySuffix[];
    besoinsIntrantsGars?: IBesoinIntrantMySuffix[];
}

export class CultureMySuffix implements ICultureMySuffix {
    constructor(
        public id?: number,
        public engraisID?: number,
        public libelle?: string,
        public prixCession?: number,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public besoinsIntrantsEscs?: IBesoinIntrantMySuffix[],
        public besoinsIntrantsGars?: IBesoinIntrantMySuffix[]
    ) {
        this.estActif = false;
    }
}
