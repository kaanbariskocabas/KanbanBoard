import { Component, OnInit } from "@angular/core";
import { HomeService } from "./home.service";
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem
} from "@angular/cdk/drag-drop";
import { HttpResponse } from "@angular/common/http";
import { Task, StatusType } from "../model/task.model";
import { TaskService } from "../service/task.service";

interface Alert {
  type: String
    message: String
}

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"]
})
export class HomeComponent implements OnInit {
  constructor(
    private homeService: HomeService,
    private taskService: TaskService
  ) {}
  
  alert: Alert = {
    type: "info",
    message: "Double click the tasks to see details"
  }
  selected: Task;
  showDetails = false;
  todo: Task[] = [];
  inprogress: Task[] = [];
  waiting: Task[] = [];
  done: Task[] = [];

  ngOnInit() {
    this.homeService.getTasks().subscribe((resp: HttpResponse<Task[]>) => {
      if (resp && resp.status == 200) {
        resp.body.forEach((task: Task) => {
          this.setTaskLists(task);
        });
      }
    });
  }

  close(alert: Alert) {
    this.alert = undefined;
  }

  private setTaskLists(task: Task) {
    if (task.status === StatusType.DONE) {
      this.done.push(task);
    } else if (task.status === StatusType.WAITING) {
      this.waiting.push(task);
    } else if (task.status === StatusType.INPROGRESS) {
      this.inprogress.push(task);
    } else {
      this.todo.push(task);
    }
  }

  drop(event: CdkDragDrop<Task[]>, target: string) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
      this.changeStatus(event.container.data[event.currentIndex], target);
    }
  }

  changeStatus(task: Task, newStatus: String) {
    task.status = this.getTaskStatus(newStatus);
    this.taskService.updateTask(task).subscribe((resp: HttpResponse<Task>) => {
      if (resp.status == 200) {
        alert("updated!!!");
      }
    });
  }

  getTaskStatus(newStatus: String): StatusType {
    return newStatus == StatusType.DONE
      ? StatusType.DONE
      : newStatus == StatusType.INPROGRESS
      ? StatusType.INPROGRESS
      : newStatus == StatusType.WAITING
      ? StatusType.WAITING
      : StatusType.TODO;
  }

  onClick(task: Task) {
    if(task !== this.selected) {
      this.showDetails = true;
      this.selected = task;
    } else {
      this.selected = undefined;
      this.showDetails = false;
    }
  }
}
