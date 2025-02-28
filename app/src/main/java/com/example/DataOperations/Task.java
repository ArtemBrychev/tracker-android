package com.example.DataOperations;

import java.io.Serializable;
import java.util.Date;

public class Task  implements Serializable {
    private String name;
    private String description;
    private TaskStatus status;
    private int complstatus;
    private String creationDate;

    public Task(String name, String description){
        this.name = name;
        this.description = description;
        complstatus = 0;
        creationDate = new Date().toString();
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setComplstatus(int compls){
        this.complstatus = compls;
    }

    public void changeTask(String newName, String newDescription){
        this.name = newName;
        this.description = newDescription;
    }

    public void setDate(String date){
        creationDate = date;
    }

    public String getName(){return name;}
    public String getDescription(){return description;}
    public String getCreationDate(){return creationDate;}
    public int getComplstatus(){return complstatus;}
    public TaskStatus getStatus(){return status;}

    @Override
    public String toString(){
        return "{" +
                "\"name\": " + name + ", " +
                "\"description\": " + description + ", " +
                "\"status\": " + status + ", " +
                "\"complStatus\": " + complstatus + ", " +
                "\"creationDate\": " + creationDate +
                "},";
    }
}
