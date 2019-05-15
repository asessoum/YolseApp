import { Moment } from 'moment';
import { IVenteMySuffix } from 'app/shared/model/vente-my-suffix.model';

export interface IArticleMySuffix {
    id?: number;
    articleID?: string;
    localID?: number;
    remoteID?: number;
    libelle?: string;
    description?: string;
    prixDeVente?: number;
    prixDeRevient?: number;
    margeBrute?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    categorieId?: number;
    ventes?: IVenteMySuffix[];
}

export class ArticleMySuffix implements IArticleMySuffix {
    constructor(
        public id?: number,
        public articleID?: string,
        public localID?: number,
        public remoteID?: number,
        public libelle?: string,
        public description?: string,
        public prixDeVente?: number,
        public prixDeRevient?: number,
        public margeBrute?: number,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public categorieId?: number,
        public ventes?: IVenteMySuffix[]
    ) {
        this.estActif = this.estActif || false;
    }
}
