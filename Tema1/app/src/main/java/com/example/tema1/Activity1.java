package com.example.tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity1 extends AppCompatActivity {
    private Fragment1A1 fragment1A1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        fragment1A1 = (Fragment1A1) getSupportFragmentManager().findFragmentById(R.id.f1a1);
    }

    public void RedirectToA2(View view){
        Intent intent = new Intent(Activity1.this, Activity2.class);
        startActivity(intent);
    }
}
