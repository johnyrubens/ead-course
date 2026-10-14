package com.ead.course.services.impl;

import com.ead.course.repositories.CourseUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseUserServiceImpl {
    final CourseUserRepository courseUserRepository;
}
