import { Component, OnInit, Input } from '@angular/core';
import GoogleAuth = gapi.auth2.GoogleAuth;
import GoogleUser = gapi.auth2.GoogleUser;
import {UserService} from '../../services/user.service/user.service'

@Component({
    selector: 'google-sign-in',
    template : require('./google-sign-in.template.html'),
    host: { '[id]': 'id' },
    providers : [UserService]
})
export class GoogleSignIn implements OnInit {

    @Input('google-id') googleId: string;

    id : String;

    auth: GoogleAuth;
    
    constructor(private userService: UserService) {
        
    }

    generateId(length:number):string {

        const chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';

        let result = '';
        for (let i = length; i > 0; --i) {
            result += chars[Math.floor(Math.random() * chars.length)];
        }
        return result;
    }

    ngOnInit():any {
        this.id = `gbutton-${this.generateId(8)}`;
        console.log(gapi);

        this.loadAuth2();

    }

    private loadAuth2():void {
        gapi.load('auth2', () => {
            // Retrieve the singleton for the GoogleAuth library and set up the client.
            this.auth = gapi.auth2.init({
                client_id: this.googleId,
                cookiepolicy: 'single_host_origin',
                // Request scopes in addition to 'profile' and 'email'
                //scope: 'additional_scope'
            });

            this.attachSignin(document.getElementById(this.id));
        });
    }

    private onAuth(user: GoogleUser) {
        let token = user.getAuthResponse().id_token;

        this.userService.authUser(token);
    }

    private attachSignin(element: HTMLElement) {
        console.log(element.id);
        this.auth.attachClickHandler(
            element, {},
            this.onAuth.bind(this),
            (error: string) => {
                console.error(JSON.stringify(error, undefined, 2));
            });
    }



}