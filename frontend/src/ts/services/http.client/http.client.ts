import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';

@Injectable()
export class HttpClient {

    private baseUrl : String = 'http://localhost:9000';

    constructor(private http: Http) {}

    createAuthorizationHeader(headers:Headers) {
        headers.append('Authorization', 'Basic ' +
            btoa('username:password'));
    }

    get(url) {

        console.debug( `Get URL: ${url}` );

        let headers = new Headers();
        this.createAuthorizationHeader(headers);
        return this.http.get(`${this.baseUrl}${url}`, {
            headers: headers
        });
    }

    post(url, data) {
        let headers = new Headers();
        this.createAuthorizationHeader(headers);
        return this.http.post(url, data, {
            headers: headers
        });
    }
}