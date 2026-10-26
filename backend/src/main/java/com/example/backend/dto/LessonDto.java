package com.example.backend.dto;

public record LessonDto(
        Long id,
        String title,
        String content,
        Integer lessonNumber,
        String attachmentUrl,
        Long chapterId,
        String chapterTitle,
        Long subjectId,
        String subjectName
) {
}
