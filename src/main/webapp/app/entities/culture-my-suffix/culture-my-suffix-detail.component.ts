import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICultureMySuffix } from 'app/shared/model/culture-my-suffix.model';

@Component({
    selector: 'jhi-culture-my-suffix-detail',
    templateUrl: './culture-my-suffix-detail.component.html'
})
export class CultureMySuffixDetailComponent implements OnInit {
    culture: ICultureMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ culture }) => {
            this.culture = culture;
        });
    }

    previousState() {
        window.history.back();
    }
}
