package com.board;

import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public Task create(String title, String description) {
        Task t = new Task(title, description);
        tasks.add(t);
        return t;
    }

    public Optional<Task> findById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    public List<Task> listAll() {
        return Collections.unmodifiableList(tasks);
    }

    public List<Task> listByStatus(Task.Status status) {
        return tasks.stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());
    }

    public boolean update(int id, String title, String description, Task.Status status) {
        Optional<Task> opt = findById(id);
        if (opt.isEmpty()) return false;
        Task t = opt.get();
        if (title != null && !title.isBlank()) t.setTitle(title);
        if (description != null) t.setDescription(description);
        if (status != null) t.setStatus(status);
        return true;
    }

    public boolean delete(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }
}
