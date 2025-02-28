package com.example.UiAdditions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.DataOperations.Task;
import com.example.DataOperations.TaskStatus;
import com.example.trecker.R;
import com.example.trecker.TaskShow;

import java.util.HashMap;
import java.util.Stack;

public class TaskAdaptor extends ArrayAdapter<Task> {
    private Task[] tasks;
    LayoutInflater lInflater;
    private Context context;

    public TaskAdaptor(Context context, Task[] data){
        super(context, R.layout.task_view_layout);
        tasks = new Task[data.length];
        sortByStatus(data);
        this.context = context;
        int index = 0;

    }

    @Override
    public int getCount() {
        Log.e("TaskAdapter", "getCount() called, size: " + tasks.length);
        if(tasks!=null){return tasks.length;}
        else{return 0;}
    }


    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent){
        Log.e("TaskAdapter", "In getView method");
        if (view == null) {
            lInflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = lInflater.inflate(R.layout.task_view_layout, parent, false);
        }
        TextView textView = view.findViewById(R.id.list_view_text);
        textView.setText(tasks[position].getName());

        TaskStatus stat = tasks[position].getStatus();
        switch(stat){
            case ImportantUrgent:
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.IUcolor));
                break;
            case NotImportantNotUrgent:
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.NINUcolor));
                break;
            case NotImportantUrgent:
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.NIUcolor));
                break;
            case ImportantNotUrgent:
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.INUcolor));
                break;
        }

        view.setOnClickListener(v -> {
            Toast.makeText(parent.getContext(), "Выбрали: " + tasks[position].getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), TaskShow.class);
            intent.putExtra("task_key", tasks[position]);
            getContext().startActivity(intent);
        });

        return view;
    }

    private void sortByStatus(Task[] data) throws NullPointerException{
        HashMap<TaskStatus, Stack<Integer>> temp = new HashMap<>();
        temp.put(TaskStatus.ImportantUrgent, new Stack<Integer>());
        temp.put(TaskStatus.ImportantNotUrgent, new Stack<Integer>());
        temp.put(TaskStatus.NotImportantUrgent, new Stack<Integer>());
        temp.put(TaskStatus.NotImportantNotUrgent, new Stack<Integer>());
        for(int i = 0; i < data.length; i++){
            temp.get(data[i].getStatus()).add(i);
        }
        int index = 0;
        while(index < data.length){
            if(temp.get(TaskStatus.ImportantUrgent).size()!=0){
                tasks[index] = data[temp.get(TaskStatus.ImportantUrgent).pop()];
            }else if(temp.get(TaskStatus.ImportantNotUrgent).size()!=0){
                tasks[index] = data[temp.get(TaskStatus.ImportantNotUrgent).pop()];
            }else if(temp.get(TaskStatus.NotImportantUrgent).size()!=0){
                tasks[index] = data[temp.get(TaskStatus.NotImportantUrgent).pop()];
            }else if(temp.get(TaskStatus.NotImportantNotUrgent).size()!=0){
                tasks[index] = data[temp.get(TaskStatus.NotImportantNotUrgent).pop()];
            }
            index++;
        }
    }
}
