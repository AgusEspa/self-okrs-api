package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalRepository repository;

    @Autowired
    GoalService goalService;

    public GoalController(GoalRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Goal> getGoals() {
        return goalService.all();
    }

    @GetMapping("/{id}")
    Goal getGoal(@PathVariable Long id) {
        return goalService.one(id);
    }

    @PostMapping
    Goal createGoal(@RequestBody Goal newGoal) {
        return goalService.newGoal(newGoal);
    }

    @DeleteMapping("/{id}")
    void deleteGoal(@PathVariable Long id) {
        goalService.deleteOne(id);
    }
}
