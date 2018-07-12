import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUtiProfilMySuffix } from 'app/shared/model/uti-profil-my-suffix.model';

@Component({
    selector: 'jhi-uti-profil-my-suffix-detail',
    templateUrl: './uti-profil-my-suffix-detail.component.html'
})
export class UtiProfilMySuffixDetailComponent implements OnInit {
    utiProfil: IUtiProfilMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utiProfil }) => {
            this.utiProfil = utiProfil;
        });
    }

    previousState() {
        window.history.back();
    }
}
