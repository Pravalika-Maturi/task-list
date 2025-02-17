package com.codurance.training.tasks;

import java.util.Date;

public final class Task {
    private final long id;
    private final String description;
    private boolean done;
    private Date deadline;
    private final Date createdDate;

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = null;
        this.createdDate = new Date();
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
