package com.example.survey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class TaskConfig {
    @Bean
    public TaskScheduler scheduler() {
        ThreadPoolTaskScheduler var1 = new ThreadPoolTaskScheduler();
        var1.setPoolSize(10);
        return var1;
    }
}