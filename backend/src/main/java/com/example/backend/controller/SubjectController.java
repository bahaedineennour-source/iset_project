package com.example.backend.controller;

import com.example.backend.dto.ChapterDto;
import com.example.backend.dto.SubjectDto;
import com.example.backend.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final CourseService courseService;

    public SubjectController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<SubjectDto> getAllSubjects() {
        return courseService.findAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectDto getSubjectById(@PathVariable Long id) {
        return courseService.findSubjectById(id);
    }

    @GetMapping("/{id}/chapters")
    public List<ChapterDto> getChaptersBySubjectId(@PathVariable Long id) {
        return courseService.findChaptersBySubjectId(id);
    }
}
