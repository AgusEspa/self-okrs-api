package me.projects.SelfOKRs;

import me.projects.SelfOKRs.domain.Goal;
import me.projects.SelfOKRs.domain.Task;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.TaskRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabaseWithGoals(TaskRepository repositoryT, GoalRepository repositoryG) {
        return args -> {
            repositoryT.save(new Task("Complete online course", (repositoryG.save(new Goal("Learn Swedish", 10)))));
        };
    }
}