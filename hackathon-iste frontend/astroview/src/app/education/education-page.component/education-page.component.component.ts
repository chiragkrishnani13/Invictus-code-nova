import { Component } from '@angular/core';

@Component({
  selector: 'app-education-page.component',
  templateUrl: './education-page.component.component.html',
  styleUrls: ['./education-page.component.component.css']
})
export class EducationPageComponentComponent 
{
     selected: string | null = null;

  select(option: string) {
    this.selected = option;
  }
}
