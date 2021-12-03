package me.projects.SelfOKRs.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "GOAL")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @NotEmpty
    private String name;

    private final LocalDate createdAt = LocalDate.now();

    @Min(1)
    @Max(5)
    private int importance;

    @Min(1)
    @Max(100)
    private int progressPercentage;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @JoinTable(
            name = "GOAL_TASK",
            joinColumns = @JoinColumn(name = "GOAL_ID"),
            inverseJoinColumns = @JoinColumn(name = "TASK_ID")
    )
    private List<Task> tasks = new ArrayList<>();


    private Goal() {}

    public Goal(String name, int importance, int progressPercentage) {
        this.name = name;
        this.importance = importance;
        this.progressPercentage = progressPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Goal{" + "id= " + id + ", name= " + name + ", importance= " + importance + ", progress= " + progressPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return progressPercentage == goal.progressPercentage && Objects.equals(name, goal.name) && importance == goal.importance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, importance, progressPercentage);
    }
}
