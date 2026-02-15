package com.hackathon_iste.demo.config;

import com.hackathon_iste.demo.Repository.EducationalContentRepository;
import com.hackathon_iste.demo.Repository.QuizQuestionRepository;
import com.hackathon_iste.demo.model.EducationalContent;
import com.hackathon_iste.demo.model.QuizQuestion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final EducationalContentRepository contentRepository;
    private final QuizQuestionRepository quizRepository;

    @Override
    public void run(String... args) throws Exception {
        if (contentRepository.count() == 0) {
            log.info("Seeding database with initial educational content...");

            // Topic 1: Understanding Gravity
            EducationalContent gravity = EducationalContent.builder()
                    .title("Understanding Gravity")
                    .category("Physics")
                    .explanation(
                            "Gravity is a fundamental interaction which causes mutual attraction between all things that have mass or energy.")
                    .realWorldUse("Gravity keeps planets in orbit around the sun and keeps us grounded on Earth.")
                    .build();

            gravity = contentRepository.save(gravity);

            QuizQuestion q1 = QuizQuestion.builder()
                    .educationalContent(gravity)
                    .question("What force keeps us on the ground?")
                    .optionA("Magnetism")
                    .optionB("Gravity")
                    .optionC("Friction")
                    .correctOption("B")
                    .build();

            quizRepository.save(q1);

            // Topic 2: The Solar System
            EducationalContent solarSystem = EducationalContent.builder()
                    .title("The Solar System")
                    .category("Astronomy")
                    .explanation(
                            "The Solar System is the gravitationally bound system of the Sun and the objects that orbit it.")
                    .realWorldUse(
                            "Understanding our solar system helps us navigate space missions and understand Earth's place in the universe.")
                    .build();

            solarSystem = contentRepository.save(solarSystem);

            QuizQuestion q2 = QuizQuestion.builder()
                    .educationalContent(solarSystem)
                    .question("Which planet is known as the Red Planet?")
                    .optionA("Mars")
                    .optionB("Venus")
                    .optionC("Jupiter")
                    .correctOption("A")
                    .build();

            quizRepository.save(q2);

            log.info("Database seeding completed.");
        } else {
            log.info("Database already contains data. Skipping seeding.");
        }
    }
}
