package com.ldash;

import com.ldash.domain.Goal;
import com.ldash.domain.ImportanceLables;
import com.ldash.domain.MeterLables;
import com.ldash.domain.Task;
import com.ldash.repositories.GoalRepository;
import com.ldash.repositories.TaskRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabaseWithGoals(TaskRepository repositoryT, GoalRepository repositoryG) {
        return args -> {
            repositoryT.save(new Task("Complete online course", (repositoryG.save(new Goal("Learn Sweedish", 10)))));
        };
    }
}