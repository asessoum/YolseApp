import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISuiviChampsMySuffix } from 'app/shared/model/suivi-champs-my-suffix.model';

@Component({
    selector: 'jhi-suivi-champs-my-suffix-detail',
    templateUrl: './suivi-champs-my-suffix-detail.component.html'
})
export class SuiviChampsMySuffixDetailComponent implements OnInit {
    suiviChamps: ISuiviChampsMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ suiviChamps }) => {
            this.suiviChamps = suiviChamps;
        });
    }

    previousState() {
        window.history.back();
    }
}
