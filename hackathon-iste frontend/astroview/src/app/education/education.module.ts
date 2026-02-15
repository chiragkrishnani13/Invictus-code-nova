import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { EducationPageComponent } from './education-page.component/education-page.component';
import { QuizModalComponent } from './quiz-modal/quiz-modal.component';


@NgModule({
  declarations: [
    EducationPageComponent,
    QuizModalComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule   
  ]
})
export class EducationModule { }
