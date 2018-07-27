import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEngraisMySuffix } from 'app/shared/model/engrais-my-suffix.model';

@Component({
    selector: 'jhi-engrais-my-suffix-detail',
    templateUrl: './engrais-my-suffix-detail.component.html'
})
export class EngraisMySuffixDetailComponent implements OnInit {
    engrais: IEngraisMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ engrais }) => {
            this.engrais = engrais;
        });
    }

    previousState() {
        window.history.back();
    }
}
