package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.ObjectiveRequest;
import me.projects.SelfOKRs.entities.Objective;
import me.projects.SelfOKRs.services.ObjectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/objectives")
public class ObjectiveController {

    private final ObjectiveService objectiveService;

    @Autowired
    public ObjectiveController(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @GetMapping
    ResponseEntity<List> getObjectives() {
        return ResponseEntity.ok(objectiveService.fetchObjectives());
    }

    // Refactor, not secure, ResponseEntity<?>
//    @GetMapping("/{id}")
//    Objective getObjective(@PathVariable Long id) {
//        return objectiveService.one(id);
//    }

    @PostMapping
    ResponseEntity<Objective> createObjective(@Valid @RequestBody ObjectiveRequest objectiveRequest) {
        Objective objective = objectiveService.newObjective(objectiveRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objective);
    }

    // Refactor, not secure, ResponseEntity<?>
    @PutMapping("/{id}")
    Objective editObjective(@PathVariable Long id, @RequestBody Objective objective) {
        return objectiveService.updateObjective(id, objective);
    }

    // Refactor, not secure, ResponseEntity<?>
    @DeleteMapping("/{id}")
    void deleteObjective(@PathVariable Long id) {
        objectiveService.removeObjective(id);
    }
}
