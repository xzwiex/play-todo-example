import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpClient } from '../http.client/http.client';
import { Observable }     from 'rxjs/Observable';
import { UserInfo }     from '../../model/user.info';


@Injectable()
export class UserService {

    private userInfo: UserInfo;

    constructor( private http: HttpClient ) {}

    getUserInfo() : Observable<UserInfo> {


        return this.http.get("/user-info").map( (responseData: Response) => {
                let userInfo = responseData.json();
                this.userInfo = userInfo;

                return userInfo;
            });

    }

}