package com.codesityou.javalintokens.services;

import java.util.List;

import com.codesityou.javalintokens.entities.Task;
import com.codesityou.javalintokens.repositories.TaskRepository;

public class TaskServiceImpl implements TaskService {

    private TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task create(Task task) {
        return repository.save(task);
    }

    @Override
    public void remove(String id) {
       repository.remove(id);
    }

    @Override
    public void setFinished(String id) {
        repository.setFinished(id);
    }

    @Override
    public List<Task> findAll(String userId) {
        return repository.findAll(userId);
    }

    
}