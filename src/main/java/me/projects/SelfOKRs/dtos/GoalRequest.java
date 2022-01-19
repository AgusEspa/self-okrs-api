package me.projects.SelfOKRs.dtos;

import me.projects.SelfOKRs.entities.UserEntity;

public class GoalRequest {

    private String name;

    private int importance;


    private GoalRequest() {}

    public GoalRequest(String name, int importance, UserEntity user) {
        this.name = name;
        this.importance = importance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
