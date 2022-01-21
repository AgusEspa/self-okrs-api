package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.KeyResult;
import me.projects.SelfOKRs.services.KeyResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/keyresults")
public class KeyResultController {

    private final KeyResultService keyResultService;

    @Autowired
    public KeyResultController(KeyResultService taskService) {
        this.keyResultService = taskService;
    }

    @GetMapping
    ResponseEntity<?> getAllTasksPerGoal(@RequestParam Long goalId) {
        return ResponseEntity.ok(keyResultService.allPerGoal(goalId));
    }

    // Refactor, not secure, ResponseEntity<?>
//    @GetMapping("/{id}")
//    KeyResult getOneTask(@PathVariable Long id) {
//        return keyResultService.one(id);
//    }

    @PostMapping
    ResponseEntity<?> addKeyResult(@Valid @RequestBody KeyResultRequest keyResultRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(keyResultService.newKeyResult(keyResultRequest));
    }

    // Refactor, not secure, ResponseEntity<?>
    @PutMapping("/{id}")
    KeyResult updateKeyResult(@PathVariable Long id, @RequestBody KeyResult keyResult) {
        return keyResultService.editKeyResult(id, keyResult);
    }

    // Refactor, not secure, ResponseEntity<?>
    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id) {
        keyResultService.deleteOne(id);
    }
}
