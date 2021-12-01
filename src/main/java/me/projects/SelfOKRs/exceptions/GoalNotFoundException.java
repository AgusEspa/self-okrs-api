package me.projects.SelfOKRs.exceptions;

public class GoalNotFoundException extends RuntimeException {

    public GoalNotFoundException(Long id) {
        super("Could not find Goal " + id);
    }
}
