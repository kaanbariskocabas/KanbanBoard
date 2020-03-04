import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class LoginService {
  constructor(private http: HttpClient) {}

  private apiUrl = "/api/login";

  public login(username, password): Observable<HttpResponse<Object>> {
    return this.sendLoginRequest(username, password);
  }

  private sendLoginRequest(username, password): Observable<HttpResponse<Object>> {
    return this.http.post(this.apiUrl, null, {
      observe: "response", params: this.getLoginHeaders(username, password)
    });
  }

  private getLoginBody(username, password) {
    return {
      username: username,
      password: password
    };
  }
 
  private getLoginHeaders(username, password): HttpParams {
    let params = new HttpParams();
    return params.append("username", username).append("password", password);
  }
}
