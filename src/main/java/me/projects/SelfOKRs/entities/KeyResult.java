package me.projects.SelfOKRs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "key_results")
public class KeyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "key_result_id")
    private Long id;

    @NotBlank
    private String name;

    private LocalDate dueDate;

    @NotNull
    private Boolean isDone;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    @JsonIgnore
    private Goal goal;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

    private KeyResult() {}

    public KeyResult(String name, LocalDate dueDate, Goal goal, UserEntity user) {
        this.name = name;
        this.dueDate = dueDate;
        this.goal = goal;
        this.isDone = false;
        this.user = user;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyResult keyResult = (KeyResult) o;
        return Objects.equals(name, keyResult.name) && Objects.equals(id, keyResult.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
