package com.ead.course.services.impl;

import com.ead.course.dtos.CourseRecordDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    final CourseRepository courseRepository;
    final ModuleRepository moduleRepository;
    final LessonRepository lessonRepository;

    @Override
    public CourseModel save(CourseRecordDto courseRecordDto) {
        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseRecordDto, courseModel);
        courseModel.setCreeationDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return courseRepository.save(courseModel);
    }

    @Override
    public boolean existsByName(String name) {
        return courseRepository.existsByName(name);
    }


    @Transactional
    @Override
    public void delete(CourseModel course) {
        List<ModuleModel> moduleModelList = moduleRepository.findAllModulesIntoCourse(course.getCourseId());
        for (ModuleModel module : moduleModelList) {
            List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
            lessonRepository.deleteAll(lessonModelList);
        }
        moduleRepository.deleteAll(moduleModelList);
        courseRepository.delete(course);
    }

    public List<CourseModel> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<CourseModel> findById(UUID courseId) {
        Optional<CourseModel> courseModelOptional = courseRepository.findById(courseId);
        if (courseModelOptional.isEmpty()) {
            //
        }
        return courseModelOptional;
    }

    @Override
    public CourseModel update(CourseRecordDto courseRecordDto, CourseModel courseModel) {
        BeanUtils.copyProperties(courseRecordDto, courseModel);
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        System.out.println(LocalDateTime.now(ZoneId.of("UTC")));
        return courseRepository.save(courseModel);
    }


}
