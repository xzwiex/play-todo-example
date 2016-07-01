import { bootstrap } from '@angular/platform-browser-dynamic';

import { AppComponent } from './components/app/app.component.ts';
import {HTTP_PROVIDERS} from '@angular/http';
import {SERVICES_PROVIDER} from './services';

bootstrap(AppComponent, [ HTTP_PROVIDERS, SERVICES_PROVIDER ]);
