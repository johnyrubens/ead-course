package com.ead.course.services;

import com.ead.course.dtos.LessonRecordDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import jakarta.validation.Valid;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {

    LessonModel save(@Valid LessonRecordDto lessonRecordDto, ModuleModel moduleModel);
    Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId);
    void delete(LessonModel lessonModel);
    LessonModel update(LessonRecordDto lessonRecordDto, LessonModel lessonModel);
}
