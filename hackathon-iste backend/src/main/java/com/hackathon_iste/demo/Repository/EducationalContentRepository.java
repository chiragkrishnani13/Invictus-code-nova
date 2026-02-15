package com.hackathon_iste.demo.Repository;

import com.hackathon_iste.demo.model.EducationalContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalContentRepository extends JpaRepository<EducationalContent, Long> {
    List<EducationalContent> findByCategory(String category);
}
