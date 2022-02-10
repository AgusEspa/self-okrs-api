package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.Objective;
import me.projects.SelfOKRs.entities.KeyResult;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.ObjectiveNotFoundException;
import me.projects.SelfOKRs.exceptions.KeyResultNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.ObjectiveRepository;
import me.projects.SelfOKRs.repositories.KeyResultRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyResultService {

    @Autowired
    private final KeyResultRepository keyResultRepository;

    private final UserEntityRepository userEntityRepository;

    private final ObjectiveRepository objectiveRepository;

    Logger logger = LoggerFactory.getLogger(KeyResultService.class);

    @Autowired
    public KeyResultService(KeyResultRepository repository, UserEntityRepository userEntityRepository, ObjectiveRepository objectiveRepository) {
        this.keyResultRepository = repository;
        this.userEntityRepository = userEntityRepository;
        this.objectiveRepository = objectiveRepository;
    }


    public List<KeyResult> allPerObjective(Long objectiveId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Objective objective = objectiveRepository.findById(objectiveId)
                .orElseThrow(() -> new ObjectiveNotFoundException(objectiveId));
        if (objective.getUser().getEmailAddress().equals(username)) {
            return keyResultRepository.findAllPerObjective(objectiveId);
        } else throw new UserEntityNotFoundException(username);
    }

    // Refactor, not secure
//    public KeyResult one(Long id) {
//        return keyResultRepository.findById(id)
//                .orElseThrow(() -> new TaskNotFoundException(id));
//    }

    public KeyResult newKeyResult(KeyResultRequest keyResultRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        Objective objective = objectiveRepository.getById(keyResultRequest.getGoalId());

        return keyResultRepository.save(new KeyResult(keyResultRequest.getName(), objective, keyResultRequest.getDueDate(), keyResultRequest.getIsDone(), user));
    }

    // Refactor, not secure
    public void deleteOne(Long id) {
        keyResultRepository.deleteById(id);
    }

    // Refactor, not secure
    public KeyResult editKeyResult(Long id, KeyResult editedKeyResult) {
        return keyResultRepository.findById(id)
                .map(task -> {
                    task.setName(editedKeyResult.getName());
                    task.setDueDate(editedKeyResult.getDueDate());
                    return keyResultRepository.save(task);
                })
                .orElseThrow(() -> new KeyResultNotFoundException(id));
    }

}
