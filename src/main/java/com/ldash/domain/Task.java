package com.ldash.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private LocalDate dueDate;

    @ManyToOne(targetEntity=Goal.class)
    private Goal goal;

    private Task() {}

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, LocalDate dueDate) {
        this.name = name;
        this.dueDate = dueDate;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Goal getGoal() {
        return goal;
    }

    @Override
    public String toString() {
        return name + ", due on " + dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dueDate);
    }
}
