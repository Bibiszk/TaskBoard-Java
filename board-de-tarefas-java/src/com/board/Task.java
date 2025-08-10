package com.board;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private static final AtomicInteger counter = new AtomicInteger(1);

    private final int id;
    private String title;
    private String description;
    private Status status;
    private final LocalDateTime createdAt;

    public enum Status {
        TODO,
        IN_PROGRESS,
        DONE
    }

    public Task(String title, String description) {
        this.id = counter.getAndIncrement();
        this.title = title;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("[#%d] %s — %s (status: %s) — criado: %s",
                id, title, description, status, createdAt.toString());
    }
}
