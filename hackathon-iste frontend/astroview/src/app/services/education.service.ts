import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuizSubmitResponse } from '../education/interfaces/quiz-submit-response';

@Injectable({ providedIn: 'root' })
export class EducationService {
  private BASE_URL = 'http://localhost:8088/api/education';

  constructor(private http: HttpClient) {}

  getAllContent() {
    return this.http.get<any[]>(this.BASE_URL);
  }

  getQuiz(contentId: number) {
    return this.http.get<any[]>(`${this.BASE_URL}/${contentId}/quiz`);
  }

 submitQuiz(payload: any) {
  return this.http.post<QuizSubmitResponse>(
    `${this.BASE_URL}/quiz/submit`,
    payload
  );
}
}
