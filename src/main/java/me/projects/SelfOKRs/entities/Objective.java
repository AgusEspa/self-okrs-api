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
@Table(name = "objectives")
public class Objective {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "objective_id")
    private Long id;

    @NotBlank
    private String name;

    private final LocalDate createdAt = LocalDate.now();

    @NotNull
    @Min(1)
    @Max(5)
    private int importance;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "objective", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<KeyResult> keyResults = new HashSet<>();


    Objective() {}

    public Objective(String name, int importance, UserEntity user) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return Objects.equals(id, objective.id) && Objects.equals(name, objective.name) && Objects.equals(createdAt, objective.createdAt) && Objects.equals(user, objective.user) && Objects.equals(importance, objective.importance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, importance, user);
    }
}