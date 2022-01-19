package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.dtos.RegistrationForm;
import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.UserEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GoalRepositoryTest {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    UserEntityRepository userEntityRepository;

    Goal goal1;
    Goal goal2;

    @BeforeAll
    public void setUp() {
        UserEntity user = new UserEntity("Testing User", "test@mail.com", "pass");
        userEntityRepository.save(user);
        goal1 = new Goal("Testing goal 1",1, user);
        goal2 = new Goal("Testing goal 2",5, user);
        goalRepository.save(goal1);
        goalRepository.save(goal2);

    }
    @AfterAll
    public void tearDown() {
        goalRepository.deleteAll();
    }

    // Test Create operation
    @Test
    public void shouldSaveGoal() {
        Goal goal = new Goal("Testing goal 3",3, null);
        Goal savedGoal = goalRepository.save(goal);
        assertEquals(savedGoal, goal);
    }

    // Test Read operation for All
    @Test
    public void shouldReturnListOfAllGoals() {
        List<Goal> goalList = goalRepository.findAll();
        assertEquals(goal1, goalList.get(0));
        assertEquals(goal2, goalList.get(1));
    }

    // Test Update operation
    // Test Delete operation

}
