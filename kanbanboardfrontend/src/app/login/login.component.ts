import { Component, OnInit } from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  FormControl,
  Validators
} from "@angular/forms";
import { LoginService } from "./login.service";
import { Router } from "@angular/router";

class LoginModel {
  username;
  password;
}

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"]
})
export class LoginComponent implements OnInit {
  hide = true;
  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private router: Router
  ) {}

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.loginForm = this.formBuilder.group({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [
        Validators.required,
        Validators.maxLength(20),
        Validators.minLength(4)
      ])
    });
  }

  getErrorMessage() {
    return this.loginForm.controls.email.hasError("required")
      ? "You must enter a value"
      : this.loginForm.controls.email.hasError("email")
      ? "Not a valid email"
      : "";
  }

  getPasswordErrorMessage() {
    return this.loginForm.controls.password.hasError("required")
      ? "You must enter your password"
      : this.loginForm.controls.password.hasError("minlength")
      ? "Your password length must be at least 4"
      : this.loginForm.controls.password.hasError("maxlength")
      ? "Your password length must be lower than 20"
      : "";
  }

  onClickSubmit(email, password) {
    if (
      !this.loginForm.controls.email.invalid &&
      !this.loginForm.controls.password.invalid
    ) {
      this.getLoggedIn(email, password);
    }
  }

  private getLoggedIn(email, password) {
    this.loginService.login(email, password).subscribe(resp => {
      if (resp.status && resp.status == 200) {
        this.router.navigate(["user"]);
      } else {
        alert("Wrong credentials");
      }
    });
  }
}
