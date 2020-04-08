package com.example.tema3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3.R;
import com.example.tema3.ToDoActivity;
import com.example.tema3.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private List<User> users;
    private Context context;

    public UserAdapter(List<User> users, Context context){
        this.users = users;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView userName, userEmail, userUsername;

        public MyViewHolder(View view) {
            super(view);
            this.userName = view.findViewById(R.id.textView_name);
            this.userEmail = view.findViewById(R.id.textView_email);
            this.userUsername = view.findViewById(R.id.textView_username);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_view, parent, false);
        return new MyViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final User user = users.get(position);
        holder.userName.setText(user.getName());
        holder.userUsername.setText(user.getUsername());
        holder.userEmail.setText(user.getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ToDoActivity.class);
                intent.putExtra("userId", user.getId());
                context.startActivity(intent);
            }
        });
    }

    public List<User> getUsers() {
        return users;
    }

    public void setStudents(List<User> users) {
        this.users = users;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
