package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.Goal;
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

    Goal goal1;
    Goal goal2;

    @BeforeAll
    public void setUp() {
        goal1 = new Goal("Testing goal 1",1,100, null);
        goal2 = new Goal("Testing goal 2",5,1, null);
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
        Goal goal = new Goal("Testing goal 3",3,50, null);
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
