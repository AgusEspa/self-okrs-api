package com.ldash.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private final LocalDate createdAt = LocalDate.now();

    //@Size(min = 1, max = 5)
    private int importance;

    //@Size(min = 1, max = 100)
    private int progressPercentage;

    //@Size(min = 1, max = 100)
    private int activityMeter;

    @OneToMany(targetEntity=Task.class)
    private List<Task> tasks;

    private Goal() {}

    public Goal(String name, int progressPercentage) {
        this.name = name;
        this.progressPercentage = progressPercentage;
        this.tasks = new ArrayList<>();
    }

    public Goal(String name, int importance, int progressPercentage, int activityMeter) {
        this.name = name;
        this.importance = importance;
        this.progressPercentage = progressPercentage;
        this.activityMeter = activityMeter;
        this.tasks = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
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

    public int getActivityMeter() {
        return activityMeter;
    }

    public void setActivityMeter(int activityMeter) {
        this.activityMeter = activityMeter;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return "Goal{" + "id= " + id + ", name= " + name + ", importance= " + importance + ", progress= " + progressPercentage + ", activity= " + activityMeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return progressPercentage == goal.progressPercentage && Objects.equals(name, goal.name) && importance == goal.importance && activityMeter == goal.activityMeter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, importance, progressPercentage, activityMeter);
    }
}
