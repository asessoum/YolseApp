import { Moment } from 'moment';
import { IArticleMySuffix } from 'app/shared/model/article-my-suffix.model';

export interface ICategorieMySuffix {
    id?: number;
    categorieID?: number;
    localID?: number;
    remoteID?: number;
    libelle?: string;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    articles?: IArticleMySuffix[];
}

export class CategorieMySuffix implements ICategorieMySuffix {
    constructor(
        public id?: number,
        public categorieID?: number,
        public localID?: number,
        public remoteID?: number,
        public libelle?: string,
        public estActif?: boolean,
        public creeLe?: Moment,
        public creePar?: string,
        public modifLe?: Moment,
        public modifPar?: string,
        public articles?: IArticleMySuffix[]
    ) {
        this.estActif = this.estActif || false;
    }
}
