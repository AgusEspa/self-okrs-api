package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    ResponseEntity<?> getAllGoals() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(goalService.all());
    }

    // Refactor, not secure
    @GetMapping("/{id}")
    Goal getOneGoal(@PathVariable Long id) {
        return goalService.one(id);
    }

    @PostMapping
    ResponseEntity<?> createGoal(@RequestBody Goal goal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(goalService.newGoal(goal));
    }

    // Refactor, not secure
    @PutMapping("/{id}")
    Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return goalService.editGoal(id, goal);
    }

    // Refactor, not secure
    @DeleteMapping("/{id}")
    void deleteGoal(@PathVariable Long id) {
        goalService.deleteOne(id);
    }
}
