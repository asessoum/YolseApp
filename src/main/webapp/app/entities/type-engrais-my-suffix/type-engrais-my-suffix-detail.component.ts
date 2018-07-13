import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeEngraisMySuffix } from 'app/shared/model/type-engrais-my-suffix.model';

@Component({
    selector: 'jhi-type-engrais-my-suffix-detail',
    templateUrl: './type-engrais-my-suffix-detail.component.html'
})
export class TypeEngraisMySuffixDetailComponent implements OnInit {
    typeEngrais: ITypeEngraisMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeEngrais }) => {
            this.typeEngrais = typeEngrais;
        });
    }

    previousState() {
        window.history.back();
    }
}
