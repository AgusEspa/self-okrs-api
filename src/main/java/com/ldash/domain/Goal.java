package com.ldash.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private final LocalDate createdAt = LocalDate.now();
    private ImportanceLables importance;
    private int progressPercentage;
    private MeterLables activityMeter;

    @OneToMany(targetEntity=Task.class)
    private List<Task> tasks;

    private Goal() {}

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

    public LocalDate getCreatedAt() {
        return createdAt;
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
