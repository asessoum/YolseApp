import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEngraisClientMySuffix } from 'app/shared/model/engrais-client-my-suffix.model';

@Component({
    selector: 'jhi-engrais-client-my-suffix-detail',
    templateUrl: './engrais-client-my-suffix-detail.component.html'
})
export class EngraisClientMySuffixDetailComponent implements OnInit {
    engraisClient: IEngraisClientMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ engraisClient }) => {
            this.engraisClient = engraisClient;
        });
    }

    previousState() {
        window.history.back();
    }
}
