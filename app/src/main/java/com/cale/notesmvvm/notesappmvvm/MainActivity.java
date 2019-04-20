package com.cale.notesmvvm.notesappmvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    
    private NoteViewMoel noteViewMoel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewMoel = ViewModelProviders.of(this).get(NoteViewMoel.class);
        noteViewMoel.getAllNotes().observe(this,new Observer<List<Note>>(){

            @Override
            public void onChanged(@Nullable List<Note> notes) {
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
