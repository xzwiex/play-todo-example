import { Pipe, PipeTransform } from '@angular/core';

import { Todo } from '../model/todo';

@Pipe({
    name: 'status',
    pure : false
})
export class TodoStatusPipe implements PipeTransform {
    transform(todos: Todo[], status: string) {

        if (todos && todos.length > 0) {
            switch (status) {
                case 'finished' :
                    return todos.filter( (todo: Todo) => todo.finished );
                case 'unfinished' :
                    return todos.filter( (todo: Todo) => !todo.finished );
                default :
                    return todos;
            }
        }

        return todos;


    }
}