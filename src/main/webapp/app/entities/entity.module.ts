import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { YolseAppRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { YolseAppProvinceMySuffixModule } from './province-my-suffix/province-my-suffix.module';
import { YolseAppCommuneMySuffixModule } from './commune-my-suffix/commune-my-suffix.module';
import { YolseAppProfilMySuffixModule } from './profil-my-suffix/profil-my-suffix.module';
import { YolseAppTypeEngraisMySuffixModule } from './type-engrais-my-suffix/type-engrais-my-suffix.module';
import { YolseAppTypeCultureMySuffixModule } from './type-culture-my-suffix/type-culture-my-suffix.module';
import { YolseAppLangueMySuffixModule } from './langue-my-suffix/langue-my-suffix.module';
import { YolseAppUtilisateurMySuffixModule } from './utilisateur-my-suffix/utilisateur-my-suffix.module';
import { YolseAppUtiProfilMySuffixModule } from './uti-profil-my-suffix/uti-profil-my-suffix.module';
import { YolseAppClientMySuffixModule } from './client-my-suffix/client-my-suffix.module';
import { YolseAppEngraisClientMySuffixModule } from './engrais-client-my-suffix/engrais-client-my-suffix.module';
import { YolseAppBesoinIntrantMySuffixModule } from './besoin-intrant-my-suffix/besoin-intrant-my-suffix.module';
import { YolseAppSuiviChampsMySuffixModule } from './suivi-champs-my-suffix/suivi-champs-my-suffix.module';
import { YolseAppEngraisMySuffixModule } from './engrais-my-suffix/engrais-my-suffix.module';
import { YolseAppCultureMySuffixModule } from './culture-my-suffix/culture-my-suffix.module';
import { YolseAppReferenceMySuffixModule } from './reference-my-suffix/reference-my-suffix.module';
import { YolseAppProfileMySuffixModule } from './profile-my-suffix/profile-my-suffix.module';
import { YolseAppUtiProfileMySuffixModule } from './uti-profile-my-suffix/uti-profile-my-suffix.module';
import { YolseAppBesoinEngraisMySuffixModule } from './besoin-engrais-my-suffix/besoin-engrais-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        YolseAppRegionMySuffixModule,
        YolseAppProvinceMySuffixModule,
        YolseAppCommuneMySuffixModule,
        YolseAppProfilMySuffixModule,
        YolseAppTypeEngraisMySuffixModule,
        YolseAppTypeCultureMySuffixModule,
        YolseAppLangueMySuffixModule,
        YolseAppUtilisateurMySuffixModule,
        YolseAppUtiProfilMySuffixModule,
        YolseAppClientMySuffixModule,
        YolseAppEngraisClientMySuffixModule,
        YolseAppBesoinIntrantMySuffixModule,
        YolseAppSuiviChampsMySuffixModule,
        YolseAppEngraisMySuffixModule,
        YolseAppCultureMySuffixModule,
        YolseAppReferenceMySuffixModule,
        YolseAppProfileMySuffixModule,
        YolseAppUtiProfileMySuffixModule,
        YolseAppBesoinEngraisMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class YolseAppEntityModule {}
