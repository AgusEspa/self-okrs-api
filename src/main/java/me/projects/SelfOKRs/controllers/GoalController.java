package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.GoalRequest;
import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    ResponseEntity<List> getAllGoals() {
        return ResponseEntity.ok(goalService.all());
    }

    // Refactor, not secure, ResponseEntity<?>
//    @GetMapping("/{id}")
//    Goal getOneGoal(@PathVariable Long id) {
//        return goalService.one(id);
//    }

    @PostMapping
    ResponseEntity<Goal> createGoal(@Valid @RequestBody GoalRequest goalRequest) {
        Goal newGoal = goalService.newGoal(goalRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newGoal);
    }

    // Refactor, not secure, ResponseEntity<?>
    @PutMapping("/{id}")
    Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return goalService.editGoal(id, goal);
    }

    // Refactor, not secure, ResponseEntity<?>
    @DeleteMapping("/{id}")
    void deleteGoal(@PathVariable Long id) {
        goalService.deleteOne(id);
    }
}
