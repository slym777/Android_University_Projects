package com.example.tema2_db.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tema2_db.model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

}
