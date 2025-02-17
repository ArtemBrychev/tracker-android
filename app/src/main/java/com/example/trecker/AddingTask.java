package com.example.trecker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddingTask extends AppCompatActivity {

    private Button taskButton, treckerButton, settingsButton;
    private ImageButton returnButton;

    public View.OnClickListener menuButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.trecker_button_ad){
                Toast.makeText(AddingTask.this, "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else if(v.getId()==R.id.task_button_ad){
                toTaskActivity(v);
            }else if(v.getId()==R.id.settings_button_ad){
                toSettingsActivity(v);;
            }else{
                Toast.makeText(AddingTask.this, "При обработки события что то пошло не так", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adding_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        taskButton = findViewById(R.id.task_button_ad);
        treckerButton = findViewById(R.id.trecker_button_ad);
        settingsButton = findViewById(R.id.settings_button_ad);
        returnButton = findViewById(R.id.return_button);


        settingsButton.setOnClickListener(menuButtons);
        taskButton.setOnClickListener(menuButtons);
        treckerButton.setOnClickListener(menuButtons);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSettingsActivity(v);
            }
        });
    }

    public void toTaskActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toSettingsActivity(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}