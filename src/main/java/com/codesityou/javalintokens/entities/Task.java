package com.codesityou.javalintokens.entities;

public class Task {

    private String taskId;
    private String content;
    private String userId;
    private boolean finished;

    public Task(){
        
    }

    public Task(String taskId, String content, String userId, boolean finished) {
        this.taskId = taskId;
        this.content = content;
        this.userId = userId;
        this.finished = finished;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    

}