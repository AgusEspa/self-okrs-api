package me.projects.SelfOKRs.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class GoalRequest {

    @NotBlank(message = "Goal name must not be empty")
    private String name;

    @Min(value = 1, message = "Number must be more than 0")
    @Max(value = 5, message = "Number must be less than 6")
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
