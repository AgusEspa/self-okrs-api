package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.TaskRequest;
import me.projects.SelfOKRs.entities.Task;
import me.projects.SelfOKRs.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Refactor, not secure
    @GetMapping
    List<Task> getAllTasks() {
        return taskService.all();
    }

    // Refactor, not secure
    @GetMapping("/{id}")
    Task getOneTask(@PathVariable Long id) {
        return taskService.one(id);
    }

    @PostMapping
    Task addTask(@RequestBody TaskRequest taskRequest) {
        return taskService.newTask(taskRequest);
    }

    // Refactor, not secure
    @PutMapping("/{id}")
    Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.editTask(id, task);
    }

    // Refactor, not secure
    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id) {
        taskService.deleteOne(id);
    }
}
