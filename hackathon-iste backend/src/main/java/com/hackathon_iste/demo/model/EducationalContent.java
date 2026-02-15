package com.hackathon_iste.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "educational_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationalContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @Column(length = 150)
    private String title;

    @Column(length = 50)
    private String category;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @Column(name = "real_world_use", columnDefinition = "TEXT")
    private String realWorldUse;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "educationalContent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizQuestion> quizQuestions;
}
