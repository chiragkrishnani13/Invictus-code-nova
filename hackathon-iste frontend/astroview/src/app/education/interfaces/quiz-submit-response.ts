export interface QuizSubmitResponse {
  success: boolean;
  isCorrect: boolean;
  correctOption: string;
  message: string;
}
