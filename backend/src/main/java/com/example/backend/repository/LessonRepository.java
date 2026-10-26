package com.example.backend.repository;

import com.example.backend.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByChapterIdOrderByLessonNumberAsc(Long chapterId);

    @Query("""
            SELECT l FROM Lesson l
            JOIN FETCH l.chapter c
            JOIN FETCH c.subject
            WHERE l.id = :id
            """)
    Optional<Lesson> findByIdWithChapterAndSubject(@Param("id") Long id);
}
