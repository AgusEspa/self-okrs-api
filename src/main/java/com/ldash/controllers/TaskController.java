package com.ldash.controllers;

import com.ldash.domain.Task;
import com.ldash.exceptions.GoalNotFoundException;
import com.ldash.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
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
