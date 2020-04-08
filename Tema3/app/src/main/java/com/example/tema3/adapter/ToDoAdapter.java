package com.example.tema3.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3.AlarmFragment;
import com.example.tema3.R;
import com.example.tema3.ToDoActivity;
import com.example.tema3.model.ToDo;
import com.example.tema3.model.User;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {
    private List<ToDo> toDos;
    private Context context;

    public ToDoAdapter(List<ToDo> toDos, Context context){
        this.toDos = toDos;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView toDoTitle;

        public MyViewHolder(View view) {
            super(view);
            this.toDoTitle = view.findViewById(R.id.textView_title);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_view, parent, false);
        return new MyViewHolder(toDoView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDo toDo = toDos.get(position);
        holder.toDoTitle.setText(toDo.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmFragment alarmFragment = new AlarmFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", toDo.getTitle());
                alarmFragment.setArguments(bundle);

                ToDoActivity toDoActivity = (ToDoActivity) context;
                toDoActivity.replaceFragment(alarmFragment, R.id.alarm_fragment_layout);
            }
        });
    }

    public List<ToDo> getToDos() {
        return toDos;
    }

    public void setToDos(List<ToDo> toDos) {
        this.toDos = toDos;
    }

    @Override
    public int getItemCount() {
        return toDos.size();
    }
}
