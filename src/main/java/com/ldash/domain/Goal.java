package com.ldash.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Goal {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private ImportanceLables importance;
    private int progressPercentage;
    private MeterLables activityMeter;
    private List<Task> tasks;

    public Goal() {}

    public Goal(String name, ImportanceLables importance, int progressPercentage, MeterLables activityMeter) {
        this.name = name;
        this.importance = importance;
        this.progressPercentage = progressPercentage;
        this.activityMeter = activityMeter;
        this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImportanceLables getImportance() {
        return importance;
    }

    public void setImportance(ImportanceLables importance) {
        this.importance = importance;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public MeterLables getActivityMeter() {
        return activityMeter;
    }

    public void setActivityMeter(MeterLables activityMeter) {
        this.activityMeter = activityMeter;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return name + "\n" +
                "- " + importance + " importance" + "\n" +
                "- Progress: " + progressPercentage + "%" + "\n" +
                "- Activity: " + activityMeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return progressPercentage == goal.progressPercentage && Objects.equals(name, goal.name) && importance == goal.importance && activityMeter == goal.activityMeter && Objects.equals(tasks, goal.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, importance, progressPercentage, activityMeter, tasks);
    }
}
