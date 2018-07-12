import { Moment } from 'moment';

export interface ITypeEngraisMySuffix {
    id?: number;
    engraisID?: number;
    libelle?: string;
    prixUnitaire?: number;
    estActif?: boolean;
    creeLe?: Moment;
    creePar?: string;
    modifLe?: Moment;
    modifPar?: string;
    engraisClientsId?: number;
}

export class TypeEngraisMySuffix implements ITypeEngraisMySuffix {
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
        public engraisClientsId?: number
    ) {
        this.estActif = false;
    }
}
