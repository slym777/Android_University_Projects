package com.example.tema2_db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tema2_db.model.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("Select * from student")
    List<Student> getAllStudents();

    @Query("Select * from student where lower(full_name) like lower(:fullName) limit 1")
    Student getStudentByFullName(String fullName);

    @Insert
    void insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);
}
