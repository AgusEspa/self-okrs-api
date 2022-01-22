package me.projects.SelfOKRs.dtos;

import me.projects.SelfOKRs.exceptions.CustomMethodArgumentNotValidException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class KeyResultRequest {

    @NotBlank(message = "Key Result name must not be empty")
    private String name;

//    private LocalDate dueDate;

    @Min(value = 1, message = "Please provide a valid id number (more than 0)")
    private Long goalId;


    public KeyResultRequest(String name, Long goalId) {
        this.name = name;

//        try { this.dueDate = LocalDate.parse(dueDate); }
//        catch (Exception e) {
//            throw new CustomMethodArgumentNotValidException("Bad date format - must be yyyy-mm-dd"); }

        this.goalId = goalId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public LocalDate getDueDate() {
//        return dueDate;
//    }
//
//    public void setDueDate(LocalDate dueDate) {
//        this.dueDate = dueDate;
//    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyResultRequest that = (KeyResultRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(goalId, that.goalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, goalId);
    }
}
