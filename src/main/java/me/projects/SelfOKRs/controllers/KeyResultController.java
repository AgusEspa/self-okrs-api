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
@RequestMapping("/api/keyresults")
public class KeyResultController {

    private final KeyResultService keyResultService;

    @Autowired
    public KeyResultController(KeyResultService taskService) {
        this.keyResultService = taskService;
    }

    @GetMapping
    ResponseEntity<List> getAllTasksPerGoal(@RequestParam Long goalId) {
        return ResponseEntity.ok(keyResultService.allPerGoal(goalId));
    }

    @PostMapping
    ResponseEntity<KeyResult> addKeyResult(@Valid @RequestBody KeyResultRequest keyResultRequest) {
        KeyResult keyResult = keyResultService.newKeyResult(keyResultRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(keyResult);
    }

}
