package com.ead.course;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.controllers.CourseController;
import com.ead.course.services.CourseService;
import com.ead.course.validations.CourseValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CourseValidationTest {
    @Test
    void invalidRequestReturnsCompactErrorsWithoutCallingServices() throws Exception {
        CourseService service = mock(CourseService.class);
        AuthUserClient client = mock(AuthUserClient.class);
        try (LocalValidatorFactoryBean beanValidator = new LocalValidatorFactoryBean()) {
            beanValidator.afterPropertiesSet();
            CourseValidator validator = new CourseValidator(beanValidator, service, client);
            var mvc = MockMvcBuilders.standaloneSetup(new CourseController(service, validator)).build();
            mvc.perform(post("/courses").contentType("application/json").content("{}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorCode").value(400))
                    .andExpect(jsonPath("$.errorsDetails.name").value("Name is mandatory"))
                    .andExpect(jsonPath("$.errorsDetails.userInstructor").value("UserInstructor is mandatory"))
                    .andExpect(jsonPath("$.errorsDetails.length()").value(5))
                    .andExpect(jsonPath("$.target").doesNotExist())
                    .andExpect(jsonPath("$.model").doesNotExist());
            verifyNoInteractions(service, client);
        }
    }
}
