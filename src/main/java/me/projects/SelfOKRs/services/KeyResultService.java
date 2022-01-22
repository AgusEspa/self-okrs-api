package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.KeyResult;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.KeyResultNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.KeyResultRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.security.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyResultService {

    @Autowired
    private final KeyResultRepository keyResultRepository;

    private final UserEntityRepository userRepository;

    private final GoalRepository goalRepository;

    private final AuthenticationFacade authenticationFacade;

    Logger logger = LoggerFactory.getLogger(KeyResultService.class);

    @Autowired
    public KeyResultService(KeyResultRepository repository, UserEntityRepository userRepository, GoalRepository goalRepository, AuthenticationFacade authenticationFacade) {
        this.keyResultRepository = repository;
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.authenticationFacade = authenticationFacade;
    }


    public List<KeyResult> allPerGoal(Long goalId) {
        String username = authenticationFacade.getAuthentication().getName();
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new GoalNotFoundException(goalId));
        if (goal.getUser().getEmailAddress().equals(username)) {
            return keyResultRepository.findAllPerGoal(goalId);
        } else throw new UserEntityNotFoundException(username);
    }

    // Refactor, not secure
//    public KeyResult one(Long id) {
//        return keyResultRepository.findById(id)
//                .orElseThrow(() -> new TaskNotFoundException(id));
//    }

    public KeyResult newKeyResult(KeyResultRequest keyResultRequest) {
        String username = authenticationFacade.getAuthentication().getName();
        UserEntity user = userRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        Goal goal = goalRepository.getById(keyResultRequest.getGoalId());

        return keyResultRepository.save(new KeyResult(keyResultRequest.getName(), goal, keyResultRequest.getDueDate(), keyResultRequest.getIsDone(), user));
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
