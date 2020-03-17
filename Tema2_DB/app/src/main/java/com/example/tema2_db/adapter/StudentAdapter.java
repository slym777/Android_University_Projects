package com.example.tema2_db.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema2_db.R;
import com.example.tema2_db.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private List<Student> students;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView studentFullName, studentMark;

        public MyViewHolder(View view) {
            super(view);
            this.studentFullName = view.findViewById(R.id.textView_fullName);
            this.studentMark = view.findViewById(R.id.textView_mark);
        }
    }

    public StudentAdapter(List<Student> students){
        this.students = students;
    }
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View studentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_view, parent, false);
        return new MyViewHolder(studentView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = students.get(position);
        holder.studentFullName.setText(student.fullName);
        holder.studentMark.setText(student.mark.toString());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
