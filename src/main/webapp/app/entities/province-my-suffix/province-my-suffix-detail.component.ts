import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';

@Component({
    selector: 'jhi-province-my-suffix-detail',
    templateUrl: './province-my-suffix-detail.component.html'
})
export class ProvinceMySuffixDetailComponent implements OnInit {
    province: IProvinceMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ province }) => {
            this.province = province;
        });
    }

    previousState() {
        window.history.back();
    }
}
