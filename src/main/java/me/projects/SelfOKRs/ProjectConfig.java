package me.projects.SelfOKRs;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.Task;
import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.TaskRepository;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectConfig {

//    @Bean
//    CommandLineRunner initGoalsDatabase(GoalRepository goalRepository) {
//        return args -> {
//            goalRepository.save(new Goal("Learn Swedish", 5, 10));
//            goalRepository.save(new Goal("Develop WebApp", 5,30));
//        };
//    }
//
//    @Bean
//    CommandLineRunner initTasksDatabase(TaskRepository taskRepository) {
//        return args -> {
//            taskRepository.save(new Task("Study verbs", ));
//        };
//    }
}
