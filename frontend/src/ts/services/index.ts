import { HttpClient } from "http.client/http.client"
import { UserService } from "user.service/user.service"
// RxJS
//import 'rxjs/Rx';
import 'rxjs/add/operator/map';


export const SERVICES_PROVIDER = [
    HttpClient, UserService
];