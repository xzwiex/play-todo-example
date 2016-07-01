import { Injectable } from '@angular/core';
import { HttpClient } from '../http.client/http.http.client';
import { Observable }     from 'rxjs/Observable';


@Injectable()
export class UserService {

    constructor( private http: HttpClient ) {}

    getUserInfo() : Observable {
        this.http.get("/user-info")
    }

}