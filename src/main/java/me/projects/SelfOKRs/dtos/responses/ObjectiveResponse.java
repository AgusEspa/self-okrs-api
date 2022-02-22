package me.projects.SelfOKRs.dtos.responses;

public class ObjectiveResponse {

    private Long id;

    private String title;

    private int importance;


    ObjectiveResponse() {}

    public ObjectiveResponse(String title, int importance) {
        this.title = title;
        this.importance = importance;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

}
