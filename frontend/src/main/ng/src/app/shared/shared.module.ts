import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  declarations: [
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class SharedModule {
}
