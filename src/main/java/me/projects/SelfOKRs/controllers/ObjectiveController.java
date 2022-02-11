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

    @PostMapping
    ResponseEntity<Objective> createObjective(@Valid @RequestBody ObjectiveRequest objectiveRequest) {
        Objective objective = objectiveService.newObjective(objectiveRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objective);
    }

    @PutMapping("/{id}")
    ResponseEntity<Objective> editObjective(@PathVariable Long id, @RequestBody ObjectiveRequest editedObjective) {
        return ResponseEntity.ok(objectiveService.updateObjective(id, editedObjective));
    }

    // Refactor, not secure, ResponseEntity<?>
    @DeleteMapping("/{id}")
    void deleteObjective(@PathVariable Long id) {
        objectiveService.removeObjective(id);
    }
}
