package com.example.tema2_db.repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tema2_db.controller.ApplicationController;
import com.example.tema2_db.dao.AppDatabase;
import com.example.tema2_db.model.Student;

import java.util.List;

public class StudentRepository {
    private AppDatabase appDatabase;

    public StudentRepository(Context context){
        this.appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertStudent(final Student student, final OnStudentRepositoryActionListener listener){
        new InsertStudent(listener).execute(student);
    }

    private class InsertStudent extends AsyncTask<Student, Void, List<Student>>{
        OnStudentRepositoryActionListener listener;

        InsertStudent(OnStudentRepositoryActionListener listener){
            this.listener = listener;
        }

        @Override
        protected List<Student> doInBackground(Student... students) {
            appDatabase.studentDao().insertStudent(students[0]);
            return appDatabase.studentDao().getAllStudents();
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            listener.actionSuccess();
            listener.notifyRecyclerView(students);
        }
    }

    public void getAllStudents(final OnStudentRepositoryActionListener listener){
        new GetAllStudents(listener).execute();
    }

    private class GetAllStudents extends AsyncTask<Void, Void, List<Student>> {
        OnStudentRepositoryActionListener listener;

        GetAllStudents(OnStudentRepositoryActionListener listener){
            this.listener = listener;
        }
        @Override
        protected List<Student> doInBackground(Void ... voids) {
            return appDatabase.studentDao().getAllStudents();
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            listener.actionSuccess();
            listener.notifyRecyclerView(students);
        }
    }

    public void deleteStudent(final String studentFullName, final OnStudentRepositoryActionListener listener){
        new DeleteStudent(listener).execute(studentFullName);
    }

    private class DeleteStudent extends AsyncTask<String, Void, List<Student>>{
        OnStudentRepositoryActionListener listener;

        DeleteStudent(OnStudentRepositoryActionListener listener){
            this.listener = listener;
        }

        @Override
        protected List<Student> doInBackground(String... strings) {
            Student student = appDatabase.studentDao().getStudentByFullName(strings[0]);
            appDatabase.studentDao().deleteStudent(student);
            return appDatabase.studentDao().getAllStudents();
        }


        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            listener.actionSuccess();
            listener.notifyRecyclerView(students);
        }
    }
}
