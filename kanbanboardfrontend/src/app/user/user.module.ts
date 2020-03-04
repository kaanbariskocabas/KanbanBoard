import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserComponent } from './user.component';
import { AngularMaterialModule } from '../angular-material.module';



@NgModule({
  declarations: [
    UserComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule
  ],
  exports: [
    UserComponent
  ]
})
export class UserModule { }
