package com.example.backend.service;

import com.example.backend.dto.ChapterDto;
import com.example.backend.dto.LessonDto;
import com.example.backend.dto.LessonSummaryDto;
import com.example.backend.dto.SubjectDto;
import com.example.backend.entity.Chapter;
import com.example.backend.entity.Lesson;
import com.example.backend.entity.Subject;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.ChapterRepository;
import com.example.backend.repository.LessonRepository;
import com.example.backend.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseService {

    private final SubjectRepository subjectRepository;
    private final ChapterRepository chapterRepository;
    private final LessonRepository lessonRepository;

    public CourseService(
            SubjectRepository subjectRepository,
            ChapterRepository chapterRepository,
            LessonRepository lessonRepository
    ) {
        this.subjectRepository = subjectRepository;
        this.chapterRepository = chapterRepository;
        this.lessonRepository = lessonRepository;
    }

    public List<SubjectDto> findAllSubjects() {
        return subjectRepository.findAllByOrderByNameAsc().stream()
                .map(this::toSubjectDto)
                .toList();
    }

    public SubjectDto findSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found: " + id));
        return toSubjectDto(subject);
    }

    public List<ChapterDto> findChaptersBySubjectId(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new ResourceNotFoundException("Subject not found: " + subjectId);
        }
        return chapterRepository.findBySubjectIdOrderByChapterNumberAsc(subjectId).stream()
                .map((chapter) -> toChapterDto(chapter, subjectId))
                .toList();
    }

    public List<LessonSummaryDto> findLessonsByChapterId(Long chapterId) {
        if (!chapterRepository.existsById(chapterId)) {
            throw new ResourceNotFoundException("Chapter not found: " + chapterId);
        }
        return lessonRepository.findByChapterIdOrderByLessonNumberAsc(chapterId).stream()
                .map(this::toLessonSummaryDto)
                .toList();
    }

    public LessonDto findLessonById(Long id) {
        Lesson lesson = lessonRepository.findByIdWithChapterAndSubject(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found: " + id));
        return toLessonDto(lesson);
    }

    private SubjectDto toSubjectDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getName(), subject.getDescription());
    }

    private ChapterDto toChapterDto(Chapter chapter, Long subjectId) {
        return new ChapterDto(
                chapter.getId(),
                chapter.getTitle(),
                chapter.getChapterNumber(),
                subjectId
        );
    }

    private LessonSummaryDto toLessonSummaryDto(Lesson lesson) {
        return new LessonSummaryDto(lesson.getId(), lesson.getTitle(), lesson.getLessonNumber());
    }

    private LessonDto toLessonDto(Lesson lesson) {
        Chapter chapter = lesson.getChapter();
        Subject subject = chapter.getSubject();
        return new LessonDto(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getContent(),
                lesson.getLessonNumber(),
                lesson.getAttachmentUrl(),
                chapter.getId(),
                chapter.getTitle(),
                subject.getId(),
                subject.getName()
        );
    }
}
