package com.example.tema3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tema3.fragment.ToDoFragment;

public class ToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        ToDoFragment toDoFragment = new ToDoFragment();
        this.setDefaultFragment(toDoFragment);
    }

    private void setDefaultFragment(Fragment defaultFragment) {
        this.replaceFragment(defaultFragment, R.id.alarm_fragment_layout);
    }

    public void replaceFragment(Fragment fragment, int id) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
