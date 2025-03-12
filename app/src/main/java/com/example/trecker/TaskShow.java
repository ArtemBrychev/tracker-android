package com.example.trecker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.DataOperations.Task;
import com.example.DataOperations.TaskManager;

public class TaskShow extends AppCompatActivity {

    private Button taskButton, treckerButton, settingsButton, changeButton, deleteButton;
    private TextView taskName, textDescription;
    private TaskManager taskManager;

    public View.OnClickListener menuButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.trecker_button_ts){
                Toast.makeText(TaskShow.this,
                        "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else if(v.getId()==R.id.task_button_ts){
                toTaskActivity(v);
            }else if(v.getId()==R.id.settings_button_ts){
                toSettingsActivity(v);
            }else{
                Toast.makeText(TaskShow.this,
                        "При обработки события что то пошло не так", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_show);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        taskButton = findViewById(R.id.task_button_ts);
        treckerButton = findViewById(R.id.trecker_button_ts);
        settingsButton = findViewById(R.id.settings_button_ts);
        changeButton = findViewById(R.id.change_button);
        deleteButton = findViewById(R.id.delete_button);
        taskName = findViewById(R.id.task_name);
        textDescription = findViewById(R.id.task_description);

        taskButton.setOnClickListener(menuButtons);
        treckerButton.setOnClickListener(menuButtons);
        settingsButton.setOnClickListener(menuButtons);

        Intent currIntent = getIntent();
        Task currtask = (Task)currIntent.getSerializableExtra("task_key");
        taskName.setText(currtask.getName());
        textDescription.setText(currtask.getDescription());

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(), AddingTask.class);
                newIntent.putExtra("task_key", currtask);
                startActivity(newIntent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskManager.deletetask(currtask.getName(), currtask.getDescription());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void toSettingsActivity(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void toTaskActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}