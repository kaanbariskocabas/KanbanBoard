import { Component, OnInit } from "@angular/core";
import { HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { User } from "../model/user.model";
import { UserService } from '../service/user.service';

@Component({
  selector: "app-user",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.scss"]
})
export class UserComponent implements OnInit {
  user: User;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.getUser().subscribe(
      (resp: HttpResponse<User>) => {
        if (resp.status == 200) {
          this.user = resp.body;
        }
      },
      (err: HttpErrorResponse) => {
        alert("User could not be fetched");
      }
    );
  }
}
