package com.ldash.controllers;

import com.ldash.repositories.GoalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.ldash.domain.Goal;
import com.ldash.exceptions.GoalNotFoundException;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalRepository repository;

    public GoalController(GoalRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    List<Goal> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Goal one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GoalNotFoundException(id));
    }

    @PostMapping
    Goal newGoal(@RequestBody Goal newGoal) {
        return repository.save(newGoal);
    }


    @DeleteMapping("/{id}")
    void deleteGoal(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
}
