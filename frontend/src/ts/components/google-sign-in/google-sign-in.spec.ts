import { beforeEachProviders, describe, inject, it } from '@angular/core/testing';
import { Component } from '@angular/core';

// Load the implementations that should be tested
import { GoogleSignIn } from './google-sign-in.component';

describe('GoogleSignIn', () => {
    // provide our implementations or mocks to the dependency injector
    beforeEachProviders(() => [
        GoogleSignIn,
    ]);

    it('should generatie ID in ngOnInit', inject([ GoogleSignIn ], (signIn) => {

        signIn.ngOnInit();

        expect(signIn.id.indexOf('gbutton-')).toBe(0)

    }));

});