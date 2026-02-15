package com.hackathon_iste.demo.Repository;

import com.hackathon_iste.demo.model.UserQuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuizAttemptRepository extends JpaRepository<UserQuizAttempt, Long> {
    List<UserQuizAttempt> findByUserId(Long userId);

    List<UserQuizAttempt> findByUserIdAndQuizQuestion_EducationalContent_ContentId(Long userId, Long contentId);
}
