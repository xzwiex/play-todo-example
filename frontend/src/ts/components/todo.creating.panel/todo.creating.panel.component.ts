import { Component, Input, Output , EventEmitter} from '@angular/core';
import { Todo } from '../../model/todo';




@Component({
    selector: 'todo-creating-panel',
    template: require('./todo.creating.panel.template.html')
})
export class TodoCreatingPanelComponent {

    public todo: Todo = {id : 0, finished : false, weight : 0, text : ''};

    @Output() public onAdd = new EventEmitter<Todo>();

    constructor() {}


    addTodo(): void {
        this.onAdd.emit(this.todo);
        this.todo = {id : 0, finished : false, weight : 0, text : ''};
    }

}