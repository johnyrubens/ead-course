package com.ead.course.configs;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @Bean
    public JsonMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            var module = new SimpleModule();

            module.addSerializer(
                    LocalDateTime.class,
                    new LocalDateTimeSerializer(
                            DateTimeFormatter.ofPattern(DATETIME_FORMAT)
                    )
            );

            builder.addModule(module);
        };
    }
}
