package me.projects.SelfOKRs.dtos;

public class TaskRequest {

    private String name;

    private Long goalId;

    private TaskRequest() {}

    public TaskRequest(String name, Long goalId) {
        this.name = name;
        this.goalId = goalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }
}
