import { Component, Input, Output , EventEmitter} from '@angular/core';
import { NgClass } from '@angular/common';



@Component({
    selector: 'todo-status-panel',
    template: require('./todo.status.panel.template.html'),
    directives : [NgClass]
})
export class TodoStatusPanelComponent {

    public status : string = 'all';

    @Output() public onChange = new EventEmitter<String>();

    constructor() {}


    changeStatus(newStatus : string): void {
        this.onChange.emit(newStatus);
        this.status = newStatus;
    }

}