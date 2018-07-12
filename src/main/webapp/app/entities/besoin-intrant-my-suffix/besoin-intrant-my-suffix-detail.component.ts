import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBesoinIntrantMySuffix } from 'app/shared/model/besoin-intrant-my-suffix.model';

@Component({
    selector: 'jhi-besoin-intrant-my-suffix-detail',
    templateUrl: './besoin-intrant-my-suffix-detail.component.html'
})
export class BesoinIntrantMySuffixDetailComponent implements OnInit {
    besoinIntrant: IBesoinIntrantMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ besoinIntrant }) => {
            this.besoinIntrant = besoinIntrant;
        });
    }

    previousState() {
        window.history.back();
    }
}
