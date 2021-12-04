package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.Task;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.repositories.TaskRepository;
import me.projects.SelfOKRs.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    @Autowired
    TaskService taskService;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Task> getTasks() {
        return taskService.all();
    }

    @GetMapping("/{id}")
    Task getTask(@PathVariable Long id) {
        return taskService.one(id);
    }

    @PostMapping
    Task addTask(@RequestBody Task newTask) {
        return taskService.newTask(newTask);
    }

    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id) {
        taskService.deleteOne(id);
    }
}
