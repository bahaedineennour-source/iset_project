package com.example.backend.controller;

import com.example.backend.dto.LessonDto;
import com.example.backend.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final CourseService courseService;

    public LessonController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public LessonDto getLessonById(@PathVariable Long id) {
        return courseService.findLessonById(id);
    }
}
