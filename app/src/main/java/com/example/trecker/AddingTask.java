package com.example.trecker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.DataOperations.Task;
import com.example.DataOperations.TaskManager;
import com.example.DataOperations.TaskStatus;

import java.io.IOException;

public class AddingTask extends AppCompatActivity {

    private Button taskButton, treckerButton, settingsButton, saveButton;
    private EditText taskName, taskDescription;
    private ImageButton returnButton;
    private Spinner statusSpinner;

    public View.OnClickListener menuButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.trecker_button_ad){
                Toast.makeText(AddingTask.this,
                        "Кнопка пока не работает", Toast.LENGTH_SHORT).show();
            }else if(v.getId()==R.id.task_button_ad){
                toTaskActivity(v);
            }else if(v.getId()==R.id.settings_button_ad){
                toSettingsActivity(v);;
            }else{
                Toast.makeText(AddingTask.this,
                        "При обработки события что то пошло не так", Toast.LENGTH_SHORT).show();
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


        taskName = findViewById(R.id.name);
        taskDescription = findViewById(R.id.Description);
        taskButton = findViewById(R.id.task_button_ad);
        treckerButton = findViewById(R.id.trecker_button_ad);
        settingsButton = findViewById(R.id.settings_button_ad);
        returnButton = findViewById(R.id.return_button);

        Intent currIntent = getIntent();
        Task currtask = (Task)currIntent.getSerializableExtra("task_key");
        String taskName2 = "";
        String taskDescr = "";

        if(currtask!=null){
            taskName2 = currtask.getName();
            taskDescr = currtask.getDescription();
            taskName.setText(taskName2);
            taskDescription.setText(taskDescr);
        }

        settingsButton.setOnClickListener(menuButtons);
        taskButton.setOnClickListener(menuButtons);
        treckerButton.setOnClickListener(menuButtons);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSettingsActivity(v);
            }
        });

        statusSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.task_status,
                android.R.layout.simple_spinner_item
        );
        statusSpinner.setAdapter(adapter);

        saveButton = findViewById(R.id.saveButton);
        String finalTaskName = taskName2;
        String finalTaskDescr = taskDescr;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currtask!=null){
                    TaskManager.deletetask(finalTaskName, finalTaskDescr);
                }
                String name = taskName.getText().toString();
                String description = taskDescription.getText().toString();
                TaskStatus stat;
                switch(statusSpinner.getSelectedItem().toString()) {
                    case "Важное и срочное":
                        stat = TaskStatus.ImportantUrgent;
                        break;
                    case "Важное и несрочное":
                        stat = TaskStatus.ImportantNotUrgent;
                        break;
                    case "Неважное, но срочное":
                        stat = TaskStatus.NotImportantUrgent;
                        break;
                    case "Неважное и несрочное":
                        stat = TaskStatus.NotImportantNotUrgent;
                        break;
                    default:
                        stat = TaskStatus.NotImportantNotUrgent;
                }

                Task newTask = new Task(name, description);
                newTask.setStatus(stat);
                try{
                    TaskManager manager = TaskManager.getInstance(getApplicationContext());
                    if(manager==null){throw new IOException();}
                    manager.addTask(newTask);
                    manager.writeData();
                }catch(IOException e){
                    Toast.makeText(AddingTask.this, "Ошибка при сохранении задaчи\n"
                            +e.getMessage(), Toast.LENGTH_LONG);
                }
                Toast.makeText(AddingTask.this, "Сохранено", Toast.LENGTH_SHORT);

                toTaskActivity(v);
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