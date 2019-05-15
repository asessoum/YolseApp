/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { UtiProfileMySuffixService } from 'app/entities/uti-profile-my-suffix/uti-profile-my-suffix.service';
import { IUtiProfileMySuffix, UtiProfileMySuffix } from 'app/shared/model/uti-profile-my-suffix.model';

describe('Service Tests', () => {
    describe('UtiProfileMySuffix Service', () => {
        let injector: TestBed;
        let service: UtiProfileMySuffixService;
        let httpMock: HttpTestingController;
        let elemDefault: IUtiProfileMySuffix;
        let expectedResult;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            expectedResult = {};
            injector = getTestBed();
            service = injector.get(UtiProfileMySuffixService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new UtiProfileMySuffix(0, 0, false, currentDate, 'AAAAAAA', currentDate, 'AAAAAAA');
        });

        describe('Service methods', () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        creeLe: currentDate.format(DATE_TIME_FORMAT),
                        modifLe: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => (expectedResult = resp));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(returnedFromService);
                expect(expectedResult).toMatchObject({ body: elemDefault });
            });

            it('should create a UtiProfileMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        creeLe: currentDate.format(DATE_TIME_FORMAT),
                        modifLe: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creeLe: currentDate,
                        modifLe: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new UtiProfileMySuffix(null))
                    .pipe(take(1))
                    .subscribe(resp => (expectedResult = resp));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(returnedFromService);
                expect(expectedResult).toMatchObject({ body: expected });
            });

            it('should update a UtiProfileMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        utiProID: 1,
                        estActif: true,
                        creeLe: currentDate.format(DATE_TIME_FORMAT),
                        creePar: 'BBBBBB',
                        modifLe: currentDate.format(DATE_TIME_FORMAT),
                        modifPar: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        creeLe: currentDate,
                        modifLe: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => (expectedResult = resp));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(returnedFromService);
                expect(expectedResult).toMatchObject({ body: expected });
            });

            it('should return a list of UtiProfileMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        utiProID: 1,
                        estActif: true,
                        creeLe: currentDate.format(DATE_TIME_FORMAT),
                        creePar: 'BBBBBB',
                        modifLe: currentDate.format(DATE_TIME_FORMAT),
                        modifPar: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creeLe: currentDate,
                        modifLe: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => (expectedResult = body));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush([returnedFromService]);
                httpMock.verify();
                expect(expectedResult).toContainEqual(expected);
            });

            it('should delete a UtiProfileMySuffix', async () => {
                const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
                expect(expectedResult);
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
