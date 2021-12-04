package me.projects.SelfOKRs.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Could not find Goal " + id);
    }
}
