package com.example.tema2_db.repository;

import com.example.tema2_db.model.Student;

import java.util.List;

public interface OnStudentRepositoryActionListener {

    void notifyRecyclerView(List<Student> students);
    void actionSuccess();
    void actionFailed(String message);
}
