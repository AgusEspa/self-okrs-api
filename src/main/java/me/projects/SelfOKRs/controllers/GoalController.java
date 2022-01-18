package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.Task;
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
    List<Goal> getAllGoals() {
        return goalService.all();
    }

    @GetMapping("/{id}")
    Goal getOneGoal(@PathVariable Long id) {
        return goalService.one(id);
    }

    @PostMapping
    Goal createGoal(@RequestBody Goal goal) {
        return goalService.newGoal(goal);
    }

    @PutMapping("/{id}")
    Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return goalService.editGoal(id, goal);
    }

    @DeleteMapping("/{id}")
    void deleteGoal(@PathVariable Long id) {
        goalService.deleteOne(id);
    }

    @PostMapping("/task")
    Task createTask(@RequestBody Task task) {
        goalService.newTask(task);
    }
}
