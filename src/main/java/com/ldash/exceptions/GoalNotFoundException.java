package com.ldash.exceptions;

public class GoalNotFoundException extends RuntimeException {

    public GoalNotFoundException(Long id) {
        super("Could not find Goal " + id);
    }
}
