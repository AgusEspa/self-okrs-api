package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.pojos.GoalRequest;
import me.projects.SelfOKRs.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    List<Goal> getGoals() {
        return goalService.all();
    }

    @GetMapping("/{id}")
    Goal getGoal(@PathVariable Long id) {
        return goalService.one(id);
    }

    @PostMapping
    Goal createGoal(@RequestBody GoalRequest goalRequest) {
        return goalService.newGoal(goalRequest);
    }

    @DeleteMapping("/{id}")
    void deleteGoal(@PathVariable Long id) {
        goalService.deleteOne(id);
    }
}
