package com.codesityou.javalintokens.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.codesityou.javalintokens.entities.Task;

import org.apache.commons.lang3.RandomStringUtils;

public class TaskRepositoryImpl implements TaskRepository {

    private DataSource dataSource;

    public TaskRepositoryImpl(DataSource source){
        this.dataSource = source;
    }

    @Override
    public Task save(Task task) {
        try (Connection connection = dataSource.getConnection()){
            String taskId = RandomStringUtils.randomAlphanumeric(25);
            String content = task.getContent();
            String userId = task.getUserId();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks (task_id, user_id, content, finished) VALUES (?,?,?,?);");
            statement.setString(1, taskId);
            statement.setString(2, userId);
            statement.setString(3, content);
            statement.setBoolean(4, false);

            statement.execute();

            task.setTaskId(taskId);
            return task;

        } catch (SQLException ex){
            throw new RuntimeException();
        }
    }

    @Override
    public void remove(String id) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tasks WHERE task_id=?;");
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException ex){
            throw new RuntimeException();
        }

    }

    @Override
    public void setFinished(String id) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET finished=true WHERE task_id=?");
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException ex){
            throw new RuntimeException();
        }

    }

    @Override
    public List<Task> findAll(String userId) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE user_id=?;");
            statement.setString(1, userId);

            List<Task> results = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String taskId = resultSet.getString("task_id");
                String content = resultSet.getString("content");
                boolean finished = resultSet.getBoolean("finished");
                Task task = new Task(taskId, content, userId, finished);
                results.add(task);
            }

            return results;

        } catch (SQLException ex){
            throw new RuntimeException();
        }
    }

}