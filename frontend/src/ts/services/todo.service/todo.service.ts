import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpClient } from '../http.client/http.client';
import { Observable }     from 'rxjs/Observable';
import { Subscription }     from 'rxjs/Subscription';
import { Todo }     from '../..//model/todo';


@Injectable()
export class TodoService {


    constructor( private http: HttpClient ) {}

    todoList(): Observable<Array<Todo>> {
        return this.http.get('/todo/list' ).map(
            (response : Response) => response.json()
        );
    }

    updateTodo(todo: Todo): Observable<Todo> {
        return this.http.post('/todo/update', todo ).map(
            (response : Response) => response.json()
        );
    }

    addTodo(todo: Todo): Observable<Todo> {
        return this.http.put('/todo/add', todo ).map(
            (response : Response) => response.json()
        );
    }

/* todoList : ->
 @$http.get('/todo/list').then( (data) -> data.data )

 addTodo : (entity) ->
 @$http.put('/todo/add', entity).then( (data) -> data.data )

 updateTodo : (entity) ->
 @$http.post('/todo/update', entity).then( (data) -> data.data )*/

   /* getUserInfo() : Observable<UserInfo> {
        return this.http.get('/todo/list').map( (reponse: Response) => this.handleUserInfoResponse(reponse) );
    }


    authUser(token:string) : Observable<UserInfo> {
        return this.http.get('/login/' + token).map(
            (response : Response) => this.handleUserInfoResponse(response)
        );
    }*/


}