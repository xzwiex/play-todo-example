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

    getUserInfo() : Observable<UserInfo> {
        return this.http.get("/user-info").map( (responseData: Response) => {
            this.userInfo = responseData.json();

            return this.userInfo = responseData.json();
        });

    }


    authUser(token:string) : Subscription {
        return this.http.get(`/login/${token}`).subscribe(
            (response : Response) => {
                console.log(response);
                return response;
            },
            (error) => console.error(error)
        );
    }

}