package me.projects.SelfOKRs;

import me.projects.SelfOKRs.domain.Goal;
import me.projects.SelfOKRs.repositories.GoalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

//    @Bean
//    CommandLineRunner initDatabase(GoalRepository goalRepository) {
//        return args -> {
//            goalRepository.save(new Goal("Learn Swedish", 5, 10));
//            goalRepository.save(new Goal("Develop WebApp", 5,30));
//        };
//    }
}