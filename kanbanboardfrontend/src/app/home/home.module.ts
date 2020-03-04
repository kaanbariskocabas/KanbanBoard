import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { AngularMaterialModule } from '../angular-material.module';
import { HomeService } from './home.service';
import { TaskDetailComponent } from './task-detail/task-detail.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [
    HomeComponent,
    TaskDetailComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    NgbModule
  ],
  exports: [
    HomeComponent
  ],
  providers: [
    HomeService
  ]
})
export class HomeModule { }
