package me.projects.SelfOKRs.dtos;

import java.time.LocalDate;
import java.util.Objects;

public class TaskRequest {

    private String name;

    private LocalDate dueDate;

    private Long goalId;

    public TaskRequest(String name, LocalDate dueDate, Long goalId) {
        this.name = name;
        this.dueDate = dueDate;
        this.goalId = goalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskRequest that = (TaskRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(dueDate, that.dueDate) && Objects.equals(goalId, that.goalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dueDate, goalId);
    }
}
