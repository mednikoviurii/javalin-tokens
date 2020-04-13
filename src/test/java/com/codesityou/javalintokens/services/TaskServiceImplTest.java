package com.codesityou.javalintokens.services;

import com.codesityou.javalintokens.entities.Task;
import com.codesityou.javalintokens.repositories.TaskRepository;

import org.easymock.EasyMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

public class TaskServiceImplTest {

    private TaskRepository repository;
    private TaskServiceImpl service;

    @BeforeClass
    public void setup(){
        repository = EasyMock.mock(TaskRepository.class);
        service = new TaskServiceImpl(repository);
    }

    @Test
    public void createTest(){
        final Task task = new Task("id", "content", "userId", false);
        EasyMock.expect(repository.save(task)).andReturn(task);
        EasyMock.replay(repository);
        Task result = service.create(task);
        assertThat(result).isNotNull().isEqualTo(task);
    }

    @Test
    public void findAllTest(){
        final String userId = "userId";
        List<Task> tasks = Arrays.asList(
            new Task("1", "content", "userId", false),
            new Task("2", "content", "userId", false),
            new Task("3", "content", "userId", false),
            new Task("4", "content", "userId", false),
            new Task("5", "content", "userId", false));
        EasyMock.expect(repository.findAll(userId)).andReturn(tasks);
        EasyMock.replay(repository);
        List<Task> results = service.findAll(userId);
        assertThat(results).hasSameSizeAs(tasks).hasSameElementsAs(tasks);
    }
}