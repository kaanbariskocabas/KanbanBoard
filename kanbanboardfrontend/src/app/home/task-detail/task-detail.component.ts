import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { Task } from 'src/app/model/task.model';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.scss']
})
export class TaskDetailComponent implements OnInit, OnChanges {

  @Input("task") task:Task;

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges() {
  }

}
