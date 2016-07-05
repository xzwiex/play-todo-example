import { Component, Input, Output , EventEmitter} from '@angular/core';
import { Todo } from '../../../model/todo';




@Component({
    selector: 'todo-item',
    template: require('./todo.item.template.html')
})
export class TodoItemComponent {

    @Input() public todo: Todo;

    @Output() public onChange = new EventEmitter<Todo>();

    constructor() {}

    onTextChange(text) {
        this.todo.text = text;
        this.emitEvent();
    }

    onCheckedChange(finished) {
        this.todo.finished = finished;
        this.emitEvent();
    }

    emitEvent(): void {

        this.onChange.emit(this.todo);

    }

}