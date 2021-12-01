package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.domain.Task;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Task> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Task one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GoalNotFoundException(id));
    }

    @PostMapping
    Task newTask(@RequestBody Task newTask) {
        return repository.save(newTask);
    }

    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
