package me.projects.SelfOKRs.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    private Task() {}

    public Task(String name, Goal goal) {
        this.name = name;
        this.goal = goal;
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

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}