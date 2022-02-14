package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.Objective;
import me.projects.SelfOKRs.entities.KeyResult;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.ObjectiveNotFoundException;
import me.projects.SelfOKRs.exceptions.KeyResultNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.exceptions.UserNotAuthorizedException;
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


    public List<KeyResult> fetchKeyResultsPerGoal(Long objectiveId) {
        String username = getUsername();
        Objective objective = objectiveRepository.findById(objectiveId)
                .orElseThrow(() -> new ObjectiveNotFoundException(objectiveId));
        if (objective.getUser().getEmailAddress().equals(username)) {
            return keyResultRepository.findAllPerObjective(objectiveId);
        } else throw new UserEntityNotFoundException(username);
    }

    public KeyResult newKeyResult(Long objectiveId, KeyResultRequest keyResultRequest) {
        String username = getUsername();
        UserEntity user = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        Objective objective = objectiveRepository.getById(objectiveId);

        return keyResultRepository.save(new KeyResult(keyResultRequest.getTitle(), objective, keyResultRequest.getDueDate(), keyResultRequest.getIsDone(), user));
    }

    public KeyResult updateKeyResult(Long id, KeyResultRequest editedKeyResult) {

        KeyResult fetchedKeyResult = validateUserAndFetchKeyResult(id);

        fetchedKeyResult.setTitle(editedKeyResult.getTitle());
        fetchedKeyResult.setDueDate(editedKeyResult.getDueDate());

        return keyResultRepository.save(fetchedKeyResult);
    }

    public void removeKeyResult(Long id) {

        validateUserAndFetchKeyResult(id);
        keyResultRepository.deleteById(id);

    }

     protected KeyResult validateUserAndFetchKeyResult(Long id) {

        String username = getUsername();

        KeyResult fetchedKeyResult = keyResultRepository.findById(id)
                .orElseThrow(() -> new KeyResultNotFoundException(id));

        if (!fetchedKeyResult.getUser().getEmailAddress().equals(username)) throw new UserNotAuthorizedException(username);
        else return fetchedKeyResult;
    }

    protected String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
