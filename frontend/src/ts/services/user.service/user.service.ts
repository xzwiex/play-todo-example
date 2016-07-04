import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpClient } from '../http.client/http.client';
import { Observable }     from 'rxjs/Observable';
import { Subscription }     from 'rxjs/Subscription';
import { UserInfo }     from '../../model/user.info';


@Injectable()
export class UserService {

    private userInfo: UserInfo;


    constructor( private http: HttpClient ) {}

    private handleUserInfoResponse(response : Response): UserInfo {

        this.userInfo = response.json();

        if (this.userInfo.token) {
            this.http.setAuthToken( this.userInfo.token );
        }

        return this.userInfo;
    }

    getUserInfo() : Observable<UserInfo> {
        return this.http.get("/user-info").map( (reponse: Response) => this.handleUserInfoResponse(reponse) );
    }


    authUser(token:string) : Observable<UserInfo> {
        return this.http.get('/login/' + token).map(
            (response : Response) => this.handleUserInfoResponse(response)
        );
    }

   
}