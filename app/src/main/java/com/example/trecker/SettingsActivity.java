package com.example.trecker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    private Button taskButton, treckerButton, settingsButton;

    private Button addTaskButton;

    public View.OnClickListener menuButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.trecker_button_s){
                Toast.makeText(SettingsActivity.this, "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else if(v.getId()==R.id.task_button_s){
                toTaskActivity(v);
            }else if(v.getId()==R.id.settings_button_s){
                Toast.makeText(SettingsActivity.this, "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(SettingsActivity.this, "При обработки события что то пошло не так", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        taskButton = findViewById(R.id.task_button_s);
        treckerButton = findViewById(R.id.trecker_button_s);
        addTaskButton = findViewById(R.id.add_task_button);

        taskButton.setOnClickListener(menuButtons);
        treckerButton.setOnClickListener(menuButtons);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddingTaskActivity(v);
            }
        });
    }

    public void toTaskActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toAddingTaskActivity(View v){
        Intent intent = new Intent(this, AddingTask.class);
        startActivity(intent);
    }
}