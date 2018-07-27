import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBesoinEngraisMySuffix } from 'app/shared/model/besoin-engrais-my-suffix.model';

@Component({
    selector: 'jhi-besoin-engrais-my-suffix-detail',
    templateUrl: './besoin-engrais-my-suffix-detail.component.html'
})
export class BesoinEngraisMySuffixDetailComponent implements OnInit {
    besoinEngrais: IBesoinEngraisMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ besoinEngrais }) => {
            this.besoinEngrais = besoinEngrais;
        });
    }

    previousState() {
        window.history.back();
    }
}
