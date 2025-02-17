package com.example.trecker;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.DataOperations.TaskManager;

public class MainActivity extends AppCompatActivity {

    private Button taskButton, treckerButton, settingsButton;
    private TextView taskMenuHeader;

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public View.OnClickListener menuButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.trecker_button){
                Toast.makeText(MainActivity.this, "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else if(v.getId()==R.id.task_button){
                Toast.makeText(MainActivity.this, "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else if(v.getId()==R.id.settings_button){
                toSettingsActivity(v);
            }else{
                Toast.makeText(MainActivity.this, "При обработки события что то пошло не так", Toast.LENGTH_SHORT).show();
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
            TaskManager taskManager = new TaskManager(getApplicationContext());
            taskManager.writeData();
            taskMenuHeader.setText(taskManager.getData());
        }catch(IOException e){
            Log.e("TaskManager", e.getMessage());
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void toSettingsActivity(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


}
