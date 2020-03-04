import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { NavComponent } from 'src/app/nav/nav.component';
import { LayoutComponent } from './layout.component';



@NgModule({
  declarations: [
    LayoutComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule
  ],
  exports: [
    LayoutComponent,
  ]
})
export class LayoutModule { }
