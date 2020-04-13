package com.codesityou.javalintokens.repositories;

import java.util.List;

import com.codesityou.javalintokens.entities.Task;

public interface TaskRepository {

    Task save (Task task);

    void remove (String id);

    void setFinished(String id);

    List<Task> findAll (String userId);
}