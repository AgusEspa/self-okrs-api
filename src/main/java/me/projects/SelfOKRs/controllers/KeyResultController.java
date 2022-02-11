package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.KeyResult;

import me.projects.SelfOKRs.services.KeyResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/objectives/keyresults")
public class KeyResultController {

    private final KeyResultService keyResultService;

    @Autowired
    public KeyResultController(KeyResultService taskService) {
        this.keyResultService = taskService;
    }

    @GetMapping
    ResponseEntity<List> getKeyResultsPerObjective(@RequestParam Long objectiveId) {
        return ResponseEntity.ok(keyResultService.fetchKeyResultsPerGoal(objectiveId));
    }

    @PostMapping
    ResponseEntity<KeyResult> createKeyResult(@Valid @RequestBody KeyResultRequest keyResultRequest) {
        KeyResult keyResult = keyResultService.newKeyResult(keyResultRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(keyResult);
    }

    @PutMapping("/{id}")
    ResponseEntity<KeyResult> editKeyResult(@PathVariable Long id, @RequestBody KeyResultRequest editedKeyResult) {
        return ResponseEntity.ok(keyResultService.updateKeyResult(id, editedKeyResult));
    }

    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id) {
        keyResultService.removeKeyResult(id);
    }

}
