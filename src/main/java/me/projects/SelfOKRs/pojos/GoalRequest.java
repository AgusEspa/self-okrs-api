package me.projects.SelfOKRs.pojos;

public class GoalRequest {

    private String name;

    private int importance;

    private int progressPercentage;

    private Long userId;

    public GoalRequest(String name, int importance, int progressPercentage, Long userId) {
        this.name = name;
        this.importance = importance;
        this.progressPercentage = progressPercentage;
        this.userId = userId;
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

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
