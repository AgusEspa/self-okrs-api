package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.KeyResult;
import me.projects.SelfOKRs.services.KeyResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keyresults")
public class KeyResultController {

    private final KeyResultService taskService;

    @Autowired
    public KeyResultController(KeyResultService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    ResponseEntity<?> getAllTasksPerGoal(Long goalId) {
        return ResponseEntity.ok(taskService.allPerGoal(goalId));
    }

    // Refactor, not secure, ResponseEntity<?>
    @GetMapping("/{id}")
    KeyResult getOneTask(@PathVariable Long id) {
        return taskService.one(id);
    }

    @PostMapping
    ResponseEntity<?> addKeyResult(@RequestBody KeyResultRequest keyResultRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.newKeyResult(keyResultRequest));
    }

    // Refactor, not secure, ResponseEntity<?>
    @PutMapping("/{id}")
    KeyResult updateTask(@PathVariable Long id, @RequestBody KeyResult keyResult) {
        return taskService.editTask(id, keyResult);
    }

    // Refactor, not secure, ResponseEntity<?>
    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id) {
        taskService.deleteOne(id);
    }
}
