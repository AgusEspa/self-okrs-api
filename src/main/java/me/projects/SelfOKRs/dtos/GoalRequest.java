package me.projects.SelfOKRs.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GoalRequest {

    @NotBlank(message = "Goal name must not be empty")
    private String name;

    @Size(
            min = 1,
            max = 5,
            message = "Number must be between 1 and 5"
    )
    private int importance;

    private GoalRequest() {}

    public GoalRequest(String name, int importance) {
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
