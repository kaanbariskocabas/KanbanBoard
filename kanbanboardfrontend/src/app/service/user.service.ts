import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {}

  private apiUrl= "api/me";

  public getUser(): Observable<HttpResponse<User>> {
    return this.requestUser();
  }

  private requestUser(): Observable<HttpResponse<User>> {
    return this.http.get<User>(this.apiUrl, {observe: "response"});
  }
}
