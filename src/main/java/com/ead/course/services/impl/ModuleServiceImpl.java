package com.ead.course.services.impl;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositores.LessonRepository;
import com.ead.course.repositores.ModuleRepository;
import com.ead.course.services.ModuleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ModuleServiceImpl implements ModuleService {
    final ModuleRepository moduleRepository;
    final LessonRepository lessonRepository;

    @Transactional
    @Override
    public void deleteModule(ModuleModel moduleModel) {
        List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(moduleModel.getModuleId());
        lessonRepository.deleteAll(lessonModelList);
        moduleRepository.delete(moduleModel);
    }


}
