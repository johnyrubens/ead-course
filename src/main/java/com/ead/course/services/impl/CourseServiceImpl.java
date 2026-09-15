package com.ead.course.services.impl;

import com.ead.course.repositores.CourseRepository;
import com.ead.course.services.ModuleService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements ModuleService {
    final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
