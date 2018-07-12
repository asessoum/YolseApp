import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfilMySuffix } from 'app/shared/model/profil-my-suffix.model';

@Component({
    selector: 'jhi-profil-my-suffix-detail',
    templateUrl: './profil-my-suffix-detail.component.html'
})
export class ProfilMySuffixDetailComponent implements OnInit {
    profil: IProfilMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profil }) => {
            this.profil = profil;
        });
    }

    previousState() {
        window.history.back();
    }
}
