import { Component, OnInit } from '@angular/core';
import { EducationService } from 'src/app/services/education.service';

@Component({
  selector: 'app-education-page',
  templateUrl: './education-page.component.html',
  styleUrls: ['./education-page.component.css']
})
export class EducationPageComponent implements OnInit {

  contents: any[] = [];
  selectedContent: any = null;
  showQuiz = false;

  constructor(private eduService: EducationService) {}

  ngOnInit(): void {
    this.eduService.getAllContent().subscribe(data => {
      this.contents = data;
    });
  }

  openQuiz(content: any) {
    this.selectedContent = content;
    this.showQuiz = true;
  }

  closeQuiz() {
    this.showQuiz = false;
  }
  selectContent(c: any) {
  this.selectedContent = c;
}
}
