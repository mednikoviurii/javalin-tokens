package com.codesityou.javalintokens;

import com.codesityou.javalintokens.controllers.TaskController;
import com.codesityou.javalintokens.controllers.UserController;
import com.codesityou.javalintokens.managers.JjwtTokenManagerImpl;
import com.codesityou.javalintokens.managers.TokenManager;
import com.codesityou.javalintokens.repositories.*;
import com.codesityou.javalintokens.services.TaskService;
import com.codesityou.javalintokens.services.TaskServiceImpl;
import com.codesityou.javalintokens.services.UserService;
import com.codesityou.javalintokens.services.UserServiceImpl;

import io.javalin.Javalin;

public class App {
    public static void main( String[] args ) throws Exception{
        
        TaskRepository taskRepository = new TaskRepositoryImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        TaskController taskController = new TaskController(taskService);

        TokenManager manager = new JjwtTokenManagerImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository, manager);
        UserController userController = new UserController(userService);

        Javalin app = Javalin.create().start(4567);

        app.before("/protected/*", userController::authorize);
        
        app.post("/protected/tasks", taskController::create);
        app.put("/protected/tasks/:id/finish", taskController::markFinished);
        app.delete("/protected/tasks/:id", taskController::remove);
        app.get("/protected/tasks/:userId", taskController::findAll);

        app.post("/signup", userController::signup);
        app.post("/login", userController::login);
    }
}
