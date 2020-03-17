package com.example.tema1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Activity2 extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment1A2 fragment1A2;
    private Fragment2A2 fragment2A2;
    private Fragment3A2 fragment3A2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        // Create and set Android Fragment as default.
        fragment1A2 = new Fragment1A2();
        this.setDefaultFragment(fragment1A2);
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void addF2A2(View view) {
        fragment2A2 = new Fragment2A2();
        Log.w("sl1m", "button_f1a2");
        addFragment(fragment2A2, R.id.dynamic_bottom_fragment_layout);
    }

    public void replaceF1A2(View view) {
        Log.w("sl1m", "button1_f2a2");
        fragment3A2 = new Fragment3A2();
        replaceFragment(fragment3A2, R.id.dynamic_top_fragment_layout);
    }

    public void eliminateF1A2(View view){
        Log.w("sl1m", "button2_f2a2");
        deleteFragment(fragment1A2);
    }

    public void closeA2(View view){
        Log.w("sl1m", "button2_f2a2");
        finish();
    }

    // This method is used to set the default fragment that will be shown.
    private void setDefaultFragment(Fragment defaultFragment) {
        this.replaceFragment(defaultFragment, R.id.dynamic_top_fragment_layout);
    }

    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment fragment, int id) {
        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

    public void addFragment(Fragment fragment, int id){
        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id, fragment);
        fragmentTransaction.commit();
    }

    public void deleteFragment(Fragment fragment){
        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
}
