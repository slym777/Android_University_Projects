package com.example.tema2_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.util.StringUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema2_db.adapter.StudentAdapter;
import com.example.tema2_db.model.Student;
import com.example.tema2_db.repository.OnStudentRepositoryActionListener;
import com.example.tema2_db.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnStudentRepositoryActionListener {
    private EditText fullName;
    private EditText mark;
    private Button addUser;
    private Button removeUser;

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private StudentRepository studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullName = findViewById(R.id.editText_fullName);
        mark = findViewById(R.id.editText_mark);
        addUser = findViewById(R.id.button_addUser);
        removeUser = findViewById(R.id.button_removeUser);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        studentRepository = new StudentRepository(getBaseContext());

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new StudentAdapter(new ArrayList<Student>());
        studentRepository = new StudentRepository(getBaseContext());
        studentRepository.getAllStudents(this);
        recyclerView.setAdapter(adapter);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Log.w("sl1m", "addUser");
                try{
                    String studentFullName = fullName.getText().toString();
                    if (TextUtils.isEmpty(studentFullName))
                        throw new Exception("Enter the name");
                    Double studentMark = Double.parseDouble(mark.getText().toString());
                    if (studentMark < 1 || studentMark > 10)
                        throw new Exception("Enter a valid mark");
                    Student student = new Student(studentFullName, studentMark);
                    studentRepository.insertStudent(student, MainActivity.this);
                } catch (Exception e){
                    if (e instanceof NullPointerException){
                        actionFailed("Enter a mark");
                    }
                    actionFailed(e.getMessage());
                }
            }
        });

        removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.w("sl1m", "removeUser");
                final String studentFullName = fullName.getText().toString();
                List<Student> studentList = adapter.getStudents();
                if (studentList.stream().filter(p -> studentFullName.equals(p.fullName)).count() < 1)
                    actionFailed("No student with such name registered");
                else
                    studentRepository.deleteStudent(studentFullName, MainActivity.this);
            }
        });
    }

    @Override
    public void notifyRecyclerView(List<Student> students) {
        List<Student> studentList = adapter.getStudents();
        studentList.clear();
        studentList.addAll(students);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void actionSuccess() {
        Toast toast = Toast.makeText(getApplicationContext(),"Executed successfully", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void actionFailed(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
