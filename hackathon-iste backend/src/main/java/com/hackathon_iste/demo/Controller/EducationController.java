package com.hackathon_iste.demo.Controller;

import com.hackathon_iste.demo.Service.EducationService;
import com.hackathon_iste.demo.model.EducationalContent;
import com.hackathon_iste.demo.model.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping
    public ResponseEntity<List<EducationalContent>> getAllContent() {
        return ResponseEntity.ok(educationService.getAllContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationalContent> getContentById(@PathVariable Long id) {
        EducationalContent content = educationService.getContentById(id);
        if (content != null) {
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/quiz")
    public ResponseEntity<List<QuizQuestion>> getQuizForContent(@PathVariable Long id) {
        return ResponseEntity.ok(educationService.getQuizForContent(id));
    }

    @PostMapping("/quiz/submit")
    public ResponseEntity<Map<String, Object>> submitQuizAttempt(@RequestBody Map<String, Object> payload) {
        // Expected payload: { "userId": 1, "questionId": 5, "selectedOption": "A" }
        try {
            Long userId = Long.valueOf(payload.get("userId").toString());
            Long questionId = Long.valueOf(payload.get("questionId").toString());
            String selectedOption = payload.get("selectedOption").toString();

            boolean isCorrect = educationService.submitQuizAttempt(userId, questionId, selectedOption);

            QuizQuestion question = educationService.getQuestionById(questionId);
            String correctOption = question != null ? question.getCorrectOption() : "";

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "isCorrect", isCorrect,
                    "correctOption", correctOption,
                    "message", isCorrect ? "Correct answer!" : "Incorrect answer."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Invalid request format"));
        }
    }

    // Admin endpoints to add content (Optional/For testing)
    @PostMapping
    public ResponseEntity<EducationalContent> createContent(@RequestBody EducationalContent content) {
        return ResponseEntity.ok(educationService.createContent(content));
    }

    @PostMapping("/{id}/questions")
    public ResponseEntity<QuizQuestion> addQuestion(@PathVariable Long id, @RequestBody QuizQuestion question) {
        try {
            return ResponseEntity.ok(educationService.addQuestion(id, question));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
