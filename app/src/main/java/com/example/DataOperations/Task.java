package com.example.DataOperations;

import java.util.Date;

public class Task {
    private String name;
    private String description;
    private TaskStatus status;
    private int complstatus;
    private final Date creationDate;
    private Date deadline;

    public Task(String name, String description){
        this.name = name;
        this.description = description;
        complstatus = 0;
        creationDate = new Date();
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setComplstatus(int compls){
        this.complstatus = compls;
    }

    public void changeTask(String newName, String newDescription){
        this.name = newName;
        this.description = newDescription;
    }

    @Override
    public String toString(){
        return "{" +
                "\"name\": " + name + ", " +
                "\"description\": " + description + ", " +
                "\"status\": " + status + ", " +
                "\"complStatus\": " + complstatus + ", " +
                "\"creationDate\": " + creationDate + ", " +
                "\"deadline: \"" + deadline +
                "}";
    }
}
