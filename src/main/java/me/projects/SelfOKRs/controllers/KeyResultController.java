package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.KeyResult;
import me.projects.SelfOKRs.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class KeyResultController {

    private final TaskService taskService;

    @Autowired
    public KeyResultController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Refactor, not secure
    @GetMapping
    List<KeyResult> getAllTasks() {
        return taskService.all();
    }

    // Refactor, not secure
    @GetMapping("/{id}")
    KeyResult getOneTask(@PathVariable Long id) {
        return taskService.one(id);
    }

    @PostMapping
    ResponseEntity<?> addTask(@RequestBody KeyResultRequest taskRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.newTask(taskRequest));
    }

    // Refactor, not secure
    @PutMapping("/{id}")
    KeyResult updateTask(@PathVariable Long id, @RequestBody KeyResult keyResult) {
        return taskService.editTask(id, keyResult);
    }

    // Refactor, not secure
    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id) {
        taskService.deleteOne(id);
    }
}
