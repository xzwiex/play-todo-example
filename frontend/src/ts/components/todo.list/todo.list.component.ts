import { Component, OnInit } from '@angular/core';
import { Todo } from '../../model/todo';
import { NgFor } from "@angular/common";
import { TodoService } from "../../services/todo.service/todo.service";
import { TodoItemComponent } from '../todo.item/todo.item.component';
import { TodoCreatingPanelComponent } from '../todo.creating.panel/todo.creating.panel.component';

@Component({
    selector: 'todo-list',
    template: require('./todo.list.template.html'),
    directives : [NgFor, TodoItemComponent, TodoCreatingPanelComponent],
    providers : [TodoService]
})

export class TodoListComponent implements OnInit {

    todos: Array<Todo>;

    constructor(private todoService: TodoService) {}


    ngOnInit() {

        this.todoService.todoList().subscribe(
            (todos) => this.todos = todos
        )
    }

    updateTodo(todo: Todo) {
        this.todoService.updateTodo(todo).subscribe(
            (updated: Todo) => console.debug('Todo updated', todo),
            (error) => console.error('Error updating todo', error)
        );
    }

    addTodo(todo: Todo) {

        this.todoService.addTodo(todo).subscribe(
            (created: Todo) => {
                this.todos.push(created);
            },
            (error) => console.error('Error creating todo', error)
        );
        //console.log('Adding todo', todo);
    }



}