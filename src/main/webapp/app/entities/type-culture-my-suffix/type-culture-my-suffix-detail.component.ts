import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeCultureMySuffix } from 'app/shared/model/type-culture-my-suffix.model';

@Component({
    selector: 'jhi-type-culture-my-suffix-detail',
    templateUrl: './type-culture-my-suffix-detail.component.html'
})
export class TypeCultureMySuffixDetailComponent implements OnInit {
    typeCulture: ITypeCultureMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeCulture }) => {
            this.typeCulture = typeCulture;
        });
    }

    previousState() {
        window.history.back();
    }
}
