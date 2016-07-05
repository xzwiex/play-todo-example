import { Component, OnInit, Output, EventEmitter, AfterViewChecked } from '@angular/core';
import GoogleAuth = gapi.auth2.GoogleAuth;
import GoogleUser = gapi.auth2.GoogleUser;

@Component({
    selector: 'google-sign-in',
    template : require('./google-sign-in.template.html'),
    host: { '[id]': 'id' },
})
export class GoogleSignIn implements OnInit, AfterViewChecked {

    @Output('on-auth') public onAuthCallback = new EventEmitter();

    id : string;

    auth: GoogleAuth;

    ngOnInit() : any {

        this.id = `gbutton-${this.generateId(8)}`;

    }

    public ngAfterViewChecked(): void {

        this.loadAuth2();
    }


    private loadAuth2():void {
        gapi.load('auth2', () => {
            // Retrieve the singleton for the GoogleAuth library and set up the client.
            this.auth = gapi.auth2.init({
                /*  client_id: this.googleId,*/
                cookie_policy: 'single_host_origin',
                // Request scopes in addition to 'profile' and 'email'
                //scope: 'additional_scope'
            });

            this.attachSignin(document.getElementById(this.id));
        });
    }

    private onAuth(user: GoogleUser) {
        this.onAuthCallback.emit({
            value : user
        });
    }

    private attachSignin(element: HTMLElement) {
        
        this.auth.attachClickHandler(
            element, {},
            (user: GoogleUser) => this.onAuth(user),
            (error: string) => {
                console.error(JSON.stringify(error, undefined, 2));
            });
    }

    private generateId(length:number):string {

        const chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';

        let result = '';
        for (let i = length; i > 0; --i) {
            result += chars[Math.floor(Math.random() * chars.length)];
        }
        return result;
    }


}