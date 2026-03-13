package mil.t2com.moda.todo.task;

import jakarta.persistence.*;
import mil.t2com.moda.todo.category.Category;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Boolean isComplete;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Task(String title, String description, Boolean isComplete, Category category) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
        this.category = category;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getIsComplete() { return isComplete; }

    public Task setIsComplete(Boolean isComplete) { this.isComplete = isComplete; return this; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) {
        this.category = category;
    }
}