import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Task } from '../model/task.model';

@Injectable({
  providedIn: "root"
})
export class HomeService {
  constructor(private http: HttpClient) {}

  private apiUrl = "/api/task"

  public getTasks(): Observable<HttpResponse<Task[]>> {
    return this.requestTasks(); 
  }

  private requestTasks(): Observable<HttpResponse<Task[]>> {
    return this.http.get<Task[]>(this.apiUrl, {observe: "response"});
  }

}
