import {Injectable} from '@angular/core';
import {Http, Headers, Response, URLSearchParams} from '@angular/http';
import { Observable } from 'rxjs/Rx';

const LOCALSTORAGE_KEY = 'todo_token';



@Injectable()
export class HttpClient {


    /*TODO: move baseUrl to config*/

    private baseUrl : string = 'http://localhost:9000';

    private authToken: string;

    constructor(private http: Http) {
        this.authToken = (localStorage.getItem(LOCALSTORAGE_KEY) || '').toString();
    }


    get( url : string, params : Map<string, any> = new Map<string, any>() ) : Observable<Response> {

        //console.debug( 'Get URL:', url );

        const search = new URLSearchParams();

        params.forEach( (v: any, idx: string, map: Map<string, any>) => {
            params.set(idx, v);
        });

        let headers = new Headers();
        this.createAuthorizationHeader(headers);
        
        return this.http.get( this.baseUrl + url, {
            headers: headers,
            search : search
        }).catch(this.handleError);
    }

    post(url, data) : Observable<Response> {
        let headers = new Headers();
        this.createAuthorizationHeader(headers);
        return this.http.post(url, data, {
            headers: headers
        });
    }

    put(url, data) : Observable<Response> {
        let headers = new Headers();
        this.createAuthorizationHeader(headers);
        return this.http.put(url, data, {
            headers: headers
        });
    }

    setAuthToken(token: string):void {
        this.authToken = token;
        localStorage.setItem(LOCALSTORAGE_KEY, token);
    }
    
    removeAuthToken():void {
        this.authToken = null;
        localStorage.removeItem(LOCALSTORAGE_KEY);
    }


    private createAuthorizationHeader(headers:Headers) {

        if (this.authToken) {
            headers.append('Authorization', 'Bearer ' + this.authToken);
        }

    }



    private handleError (error: any) {
        // In a real world app, we might use a remote logging infrastructure
        // We'd also dig deeper into the error to get a better message
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}