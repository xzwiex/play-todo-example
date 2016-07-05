import { Component, Input, Output , EventEmitter} from '@angular/core';
import { Todo } from '../../../model/todo';




@Component({
    selector: 'todo-creating-panel',
    template: require('./todo.creating.panel.template.html')
})
export class TodoCreatingPanelComponent {

    public todo: Todo = {id : 0, finished : false, text : ''};

    @Output() public onAdd = new EventEmitter<Todo>();

    constructor() {}


    addTodo(): void {

        if (this.todo.text.trim()) {
            this.onAdd.emit(this.todo);
            this.todo = {id : 0, finished : false, text : ''};
        }


    }

}