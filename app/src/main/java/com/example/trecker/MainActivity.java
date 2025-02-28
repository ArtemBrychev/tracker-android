package com.example.trecker;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import com.example.DataOperations.*;
import com.example.UiAdditions.TaskAdaptor;

public class MainActivity extends AppCompatActivity {

    private Button taskButton, treckerButton, settingsButton;
    private TextView taskMenuHeader;
    private ListView taskView;

    private TaskManager taskManager;

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public View.OnClickListener menuButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.trecker_button){
                Toast.makeText(MainActivity.this,
                        "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else if(v.getId()==R.id.task_button){
                Toast.makeText(MainActivity.this,
                        "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else if(v.getId()==R.id.settings_button){
                toSettingsActivity(v);
            }else{
                Toast.makeText(MainActivity.this,
                        "При обработки события что то пошло не так", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        treckerButton = findViewById(R.id.trecker_button);
        settingsButton = findViewById(R.id.settings_button);
        taskMenuHeader = findViewById(R.id.task_menu_header);

        settingsButton.setOnClickListener(menuButtons);
        treckerButton.setOnClickListener(menuButtons);

        try{
            taskManager = TaskManager.getInstance(getApplicationContext());
        }catch(IOException e){
            Log.e("TaskManager", e.getMessage());
        }

        Task[] tasks = new Task[taskManager.getTasks().size()];
        int index = 0;
        for(Task task : taskManager.getTasks()){
            Log.e("Task", task.toString());
            tasks[index++]=task;
        }
        taskView = findViewById(R.id.task_list_view);
        Log.e("MainActivity", "Before TaskAdaptor");
        TaskAdaptor taskAdaptor = new TaskAdaptor(this, tasks);
        taskView.setAdapter(taskAdaptor);
    }

    public void toSettingsActivity(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


}
