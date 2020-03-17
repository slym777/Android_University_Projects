package com.example.tema2_db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "full_name")
    public String fullName;

    @ColumnInfo(name = "mark")
    public Double mark;

    public Student(String fullName, Double mark){
        this.fullName = fullName;
        this.mark = mark;
    }

}
