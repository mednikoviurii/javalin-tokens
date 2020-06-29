package com.codesityou.javalintokens.repositories;

import com.codesityou.javalintokens.entities.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskRepositoryImpl implements TaskRepository{

    private List<Task> tasks;

    public TaskRepositoryImpl() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public Task save(Task task) {
        String taskId = UUID.randomUUID().toString();
        task.setTaskId(taskId);
        tasks.add(task);
        return task;
    }

    @Override
    public void remove(String id) {
        tasks.removeIf(t -> t.getTaskId().equalsIgnoreCase(id));
    }

    @Override
    public void setFinished(String id) {
        tasks.stream().filter(t -> t.getTaskId().equalsIgnoreCase(id))
                .forEach(t -> t.setFinished(true));
    }

    @Override
    public List<Task> findAll(String userId) {
        return tasks.stream().filter(t -> t.getUserId().equalsIgnoreCase(userId))
                .collect(Collectors.toList());
    }
}
