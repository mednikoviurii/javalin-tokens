package com.codesityou.javalintokens.controllers;

import java.util.List;

import com.codesityou.javalintokens.entities.Task;
import com.codesityou.javalintokens.services.TaskService;

import io.javalin.http.Context;

public class TaskController {

    private TaskService service;

    public TaskController (TaskService service){
        this.service = service;
    }

    public void create (Context context){
        Task body = context.bodyAsClass(Task.class);
        Task result = service.create(body);
        context.json(result);
    }

    public void remove (Context context){
        String id = context.pathParam("id");
        service.remove(id);
        context.status(200).result("Success");
    }

    public void findAll (Context context){
        String userId = context.pathParam("userId");
        List<Task> results = service.findAll(userId);
        context.json(results);
    }

    public void markFinished(Context context){
        String id = context.pathParam("id");
        service.setFinished(id);
        context.status(200).result("Success");
    }
}