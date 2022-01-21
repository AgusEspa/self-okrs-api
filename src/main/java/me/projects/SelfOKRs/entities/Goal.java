package me.projects.SelfOKRs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "goal_id")
    private Long id;

    @NotBlank
    private String name;

    private final LocalDate createdAt = LocalDate.now();

    @NotNull
    @Min(1)
    @Max(5)
    private int importance;

    @OneToMany(mappedBy = "goal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<KeyResult> keyResults = new HashSet<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

    private Goal() {}

    public Goal(String name, int importance, UserEntity user) {
        this.name = name;
        this.importance = importance;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<KeyResult> getTasks() {
        return keyResults;
    }

    public void setTasks(Set<KeyResult> keyResults) {
        this.keyResults = keyResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Objects.equals(id, goal.id) && Objects.equals(name, goal.name) && Objects.equals(createdAt, goal.createdAt) && Objects.equals(user, goal.user) && Objects.equals(importance, goal.importance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, importance, user);
    }
}
