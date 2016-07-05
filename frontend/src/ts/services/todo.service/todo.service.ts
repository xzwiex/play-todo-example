import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpClient } from '../http.client/http.client';
import { Observable }     from 'rxjs/Observable';
import { Subscription }     from 'rxjs/Subscription';
import { Todo }     from '../..//model/todo';



/**
 *
 * Service for working with `Todo` entities
 *
 * */

@Injectable()
export class TodoService {


    constructor( private http: HttpClient ) {}

    todoList(): Observable<Array<Todo>> {
        return this.http.get('/todo/list' ).map(this.responseHandler);
    }

    updateTodo(todo: Todo): Observable<Todo> {
        return this.http.post('/todo/update', todo ).map(this.responseHandler);
    }

    addTodo(todo: Todo): Observable<Todo> {
        return this.http.put('/todo/add', todo ).map(this.responseHandler);
    }

    private responseHandler( response: Response ) {
        return response.json();
    }


}