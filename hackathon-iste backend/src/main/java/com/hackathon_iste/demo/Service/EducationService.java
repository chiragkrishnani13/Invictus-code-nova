package com.hackathon_iste.demo.Service;

import com.hackathon_iste.demo.Repository.EducationalContentRepository;
import com.hackathon_iste.demo.Repository.QuizQuestionRepository;
import com.hackathon_iste.demo.Repository.UserQuizAttemptRepository;
import com.hackathon_iste.demo.model.EducationalContent;
import com.hackathon_iste.demo.model.QuizQuestion;
import com.hackathon_iste.demo.model.UserQuizAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationalContentRepository contentRepository;

    @Autowired
    private QuizQuestionRepository quizRepository;

    @Autowired
    private UserQuizAttemptRepository attemptRepository;

    public List<EducationalContent> getAllContent() {
        return contentRepository.findAll();
    }

    public EducationalContent getContentById(Long id) {
        return contentRepository.findById(id).orElse(null);
    }

    public List<QuizQuestion> getQuizForContent(Long contentId) {
        return quizRepository.findByEducationalContent_ContentId(contentId);
    }

    public QuizQuestion getQuestionById(Long questionId) {
        return quizRepository.findById(questionId).orElse(null);
    }

    public boolean submitQuizAttempt(Long userId, Long questionId, String selectedOption) {
        Optional<QuizQuestion> questionOpt = quizRepository.findById(questionId);
        if (questionOpt.isEmpty()) {
            return false;
        }

        QuizQuestion question = questionOpt.get();
        boolean isCorrect = question.getCorrectOption() != null &&
                question.getCorrectOption().equalsIgnoreCase(selectedOption);

        UserQuizAttempt attempt = UserQuizAttempt.builder()
                .userId(userId)
                .quizQuestion(question)
                .selectedOption(selectedOption)
                .isCorrect(isCorrect)
                .build();

        attemptRepository.save(attempt);
        return isCorrect;
    }

    // Add method to create content if needed (for admin)
    public EducationalContent createContent(EducationalContent content) {
        return contentRepository.save(content);
    }

    public QuizQuestion addQuestion(Long contentId, QuizQuestion question) {
        EducationalContent content = contentRepository.findById(contentId).orElseThrow();
        question.setEducationalContent(content);
        return quizRepository.save(question);
    }
}
