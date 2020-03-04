import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Task } from "../model/task.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class TaskService {
  constructor(private http: HttpClient) {}

  private apiUrl = "/api/task";

  public updateTask(task: Task): Observable<HttpResponse<Object>> {
    return this.requestUpdateTask(task);
  }

  private requestUpdateTask(task: Task): Observable<HttpResponse<Object>> {
    console.log(task);
    return this.http.put<Task>(this.apiUrl + "/" + task.id, task, {
      observe: "response"
    });
  }
}
