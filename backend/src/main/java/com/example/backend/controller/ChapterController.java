package com.example.backend.controller;

import com.example.backend.dto.LessonSummaryDto;
import com.example.backend.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    private final CourseService courseService;

    public ChapterController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}/lessons")
    public List<LessonSummaryDto> getLessonsByChapterId(@PathVariable Long id) {
        return courseService.findLessonsByChapterId(id);
    }
}
