/**
 * 
 */
package com.nikpetrovic.kanban.model;

/**
 * @author Nikola.Petrovic
 * @created Apr 20, 2016
 *
 */
public class Task {
    private boolean done;
    private int id;
    private String name;

    public Task() {
	super();
    }

    public Task(int id, String name, boolean done) {
	super();
	this.id = id;
	this.name = name;
	this.done = done;
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public boolean isDone() {
	return done;
    }

    public void setDone(boolean done) {
	this.done = done;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

}