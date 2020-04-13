package com.codesityou.javalintokens.services;

import java.util.List;

import com.codesityou.javalintokens.entities.Task;

public interface TaskService {

    Task create (Task task);

    void remove (String id);

    void setFinished(String id);

    List<Task> findAll (String userId);
    
}