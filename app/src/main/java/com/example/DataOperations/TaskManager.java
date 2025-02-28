package com.example.DataOperations;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.IOException;

public class TaskManager {
    private static TaskManager instance;
    private static Context context;
    private static ArrayList<Task> tasks;

    private TaskManager(Context context) throws IOException {
        tasks = new ArrayList<Task>();
        this.context = context;
        File tasksFile = new File(this.context.getFilesDir(), "Tasks.txt");
        if(!tasksFile.exists()) {
            FileOutputStream fon = context.openFileOutput("Tasks.txt", context.MODE_PRIVATE);
            Log.e("TaskManager", "File created");
            fon.close();
        }else{
            Log.e("TaskManager", "File exists");
        }

    }


    public static TaskManager getInstance(Context context) throws IOException{
        if(instance==null){
            instance = new TaskManager(context);
            getData();
        }
        return instance;
    }


    public static void getData() throws IOException{
        tasks.clear();
        StringBuilder grand = new StringBuilder();
        File file = new File(context.getFilesDir(), "Task.txt");
        try(BufferedReader input = new BufferedReader(new FileReader(file))){
            String line = "";
            while(line != null){
                grand.append(line);
                line = input.readLine();
            }
        }
        parseString(grand.toString());
    }

    public static void writeData() throws IOException{
        File file = new File(context.getFilesDir(), "Task.txt");
        if(file.exists()){Log.e("TaskManager", "File found in write function");}
        StringBuilder savingData = new StringBuilder("");
        try(BufferedWriter fout = new BufferedWriter(new FileWriter(file))){
            for(Task task : tasks){
                savingData.append(task.toString());
            }
            Log.e("TaskManager", savingData.toString());
            fout.write(savingData.toString());
        }
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    private static void parseString(String data){
        data.trim();
        String[] arr = data.split("\\},\\s*\\{");
        for(String x : arr){
            int nameIndex, descIndex, statusIndex, complsStatusIndex, creationdateIndex;
            nameIndex = x.indexOf("\"name\": ");
            descIndex = x.indexOf("\"description\": ");
            statusIndex = x.indexOf("\"status\": ");
            complsStatusIndex = x.indexOf("\"complStatus\": ");
            creationdateIndex = x.indexOf("\"creationDate\": ");
            String name = x.substring(nameIndex+8, descIndex-2);
            String description = x.substring(descIndex+15, statusIndex-2);
            String status = x.substring(statusIndex+10, complsStatusIndex-2);
            String complStatus = x.substring(complsStatusIndex+15, creationdateIndex-2);
            String creationDate = x.substring(creationdateIndex+16, creationdateIndex+44);

            Task newTask = new Task(name, description);
            newTask.setComplstatus(Integer.parseInt(complStatus));
            newTask.setStatus(TaskStatus.valueOf(status));
            newTask.setDate(creationDate);
            tasks.add(newTask);
        }
    }

/*
    public static int deletetask(String name, String description){
        for(int i = 0; i < tasks.size(); i++){
            String taskName = tasks.get(i).getName();
            String taskDescr = tasks.get(i).getDescription();
            if((taskName.equals(name))&&(taskDescr.equals(description))){
                tasks.remove(i);
                try{
                    writeData();
                    getData();
                }
                catch(IOException e){
                    Log.e("TaskManager", "Saving or reading tasks after deletetask went wrong");
                }

                return 0;
            }
        }
        return -1;
    }
*/
}
