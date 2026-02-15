import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { EducationService } from 'src/app/services/education.service';
import { QuizSubmitResponse } from 'src/app/education/interfaces/quiz-submit-response';



@Component({
  selector: 'app-quiz-modal',
  templateUrl: './quiz-modal.component.html',
  styleUrls: ['./quiz-modal.component.css']
})
export class QuizModalComponent implements OnInit {

  @Input() content: any;
  @Output() close = new EventEmitter();

  questions: any[] = [];
  selected: any = {};

  constructor(private eduService: EducationService) {}

  ngOnInit() {
    this.eduService.getQuiz(this.content.contentId).subscribe(q => {
      this.questions = q;
    });
  }

  submit(q: any) {
    const payload = {
      userId: 1,
      questionId: q.questionId,
      selectedOption: this.selected[q.questionId]
    };

    this.eduService
      .submitQuiz(payload)
      .subscribe((res: QuizSubmitResponse) => {
        alert(res.message);
      });
  }
}
