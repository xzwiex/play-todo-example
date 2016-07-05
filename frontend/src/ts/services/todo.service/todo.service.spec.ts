import { beforeEachProviders, describe, inject, it } from '@angular/core/testing';

import { Component } from '@angular/core';
import { BaseRequestOptions, Http, Response } from '@angular/http';
import { MockBackend } from '@angular/http/testing';

// Load the implementations that should be tested
import { TodoService } from './todo.service';
import { HttpClient } from '../http.client/http.client';
import { Todo } from '../../model/todo';

let mockbackend;

describe('TodoService', () => {
    // provide our implementations or mocks to the dependency injector
    beforeEachProviders(() => [
        BaseRequestOptions,
        MockBackend,
        {
            provide: Http,
            useFactory: function(backend, defaultOptions) {
                return new Http(backend, defaultOptions);
            },
            deps: [MockBackend, BaseRequestOptions]
        },
        HttpClient,
        TodoService
    ]);

    beforeEach(inject([MockBackend], (_mockbackend) => {
        mockbackend = _mockbackend;
    }));

    it('Should load todos', inject([ TodoService ], (todoService) => {


        mockbackend.connections.subscribe(connection => {
            connection.mockRespond(new Response({body: JSON.stringify([
                {id : 1, text : 'Todo 1', finished : false},
                {id : 2, text : 'Todo 2', finished : true},
                {id : 3, text : 'Todo 3', finished : false},
            ])}));
        });

        todoService.todoList().subscribe( todos => {
            expect(todos.length).toBe(3);
        } );

    }));


    it('Should update todo', inject([ TodoService ], (todoService) => {


        let updated: Todo = {id: 1, text: 'Todo 1', finished: false};

        mockbackend.connections.subscribe(connection => {
            connection.mockRespond(new Response({body: JSON.stringify(updated)}));
        });

        todoService.updateTodo(updated).subscribe( todo => {
            expect(todo).toEqual(todo);
        } );

    }));

});