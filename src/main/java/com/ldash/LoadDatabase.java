package com.ldash;

import com.ldash.domain.Goal;
import com.ldash.domain.ImportanceLables;
import com.ldash.domain.MeterLables;
import com.ldash.repositories.GoalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(GoalRepository repository) {
        return args -> {
            repository.save(new Goal("Develop my own WebApp", ImportanceLables.EXTREME, 10, MeterLables.MID));
        };
    }
}