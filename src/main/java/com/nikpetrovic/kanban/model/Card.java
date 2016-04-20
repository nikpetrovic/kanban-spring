/**
 * 
 */
package com.nikpetrovic.kanban.model;

import java.util.List;

/**
 * @author Nikola.Petrovic
 * @created Apr 20, 2016
 *
 */
public class Card {
    private String color;
    private String description;
    private long id;
    private String status;
    private List<Task> tasks;
    private String title;

    public Card() {
        super();
    }

    public Card(long id, String title, String description, String color, String status, List<Task> tasks) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
        this.status = status;
        this.tasks = tasks;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}