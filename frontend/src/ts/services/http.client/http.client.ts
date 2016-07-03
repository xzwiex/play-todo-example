import {Injectable} from '@angular/core';
import {Http, Headers, Response, URLSearchParams} from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class HttpClient {

    private baseUrl : string = 'http://localhost:9000';

    constructor(private http: Http) {}

    createAuthorizationHeader(headers:Headers) {
        headers.append('Authorization', 'Basic ' +
            btoa('username:password'));
    }

    get( url : string, params : Map<String, any> = new Map() ) : Observable<Response> {

        console.debug( 'Get URL:', url );

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

    private handleError (error: any) {
        // In a real world app, we might use a remote logging infrastructure
        // We'd also dig deeper into the error to get a better message
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}