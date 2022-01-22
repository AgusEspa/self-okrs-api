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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;


    KeyResult() {}

    public KeyResult(String name, Goal goal, LocalDate dueDate, Boolean isDone) {
        this.name = name;
        this.goal = goal;
        this.dueDate = dueDate;
        this.isDone = isDone;
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

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
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
