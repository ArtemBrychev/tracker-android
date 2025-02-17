package com.example.DataOperations;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.IOException;

public class TaskManager {
    private Context context;
    private ArrayList<Task> tasks;

    public TaskManager(Context context) throws IOException {
        tasks = new ArrayList<Task>();
        this.context = context;
        File tasks = new File(this.context.getFilesDir(), "Tasks.txt");
        if(!tasks.exists()) {
            FileOutputStream fon = context.openFileOutput("Tasks.txt", context.MODE_PRIVATE);
            fon.close();
        }else{
            Log.e("TaskManager", "File exists");
        }
    }

    public String getData() throws IOException{
        StringBuilder grand = new StringBuilder();
        File file = new File(context.getFilesDir(), "Task.txt");
        try(BufferedReader input = new BufferedReader(new FileReader(file))){
            String line = "";
            while(line != null){
                grand.append(line);
                line = input.readLine();
            }
        }
        return grand.toString();
    }

    public void writeData() throws IOException{
        File file = new File(context.getFilesDir(), "Task.txt");
        try(BufferedWriter fout = new BufferedWriter(new FileWriter(file))){
            fout.write(testString);
        }
    }

    private final String testString = "{\n" +
            "    \"name\": hello\n" +
            "    \"Description\": gfgfgfgffhfhfhfhfh,\n" +
            "    \"status\": 0,\n" +
            "    \"complStatus\": ImportantUrgent,\n" +
            "    \"creationdate\": 2024.05.07,\n" +
            "    \"deadline\": 3045.03.34\n" +
            "},\n" +
            "{\n" +
            "    \"name\": hello2\n" +
            "    \"Description\": hhhgfgfgfgffhfhfhfhfh,\n" +
            "    \"status\": 1,\n" +
            "    \"complStatus\": ImportantUrgent,\n" +
            "    \"creationdate\": 2024.05.07,\n" +
            "    \"deadline\": 3045.03.34\n" +
            "}";
}
