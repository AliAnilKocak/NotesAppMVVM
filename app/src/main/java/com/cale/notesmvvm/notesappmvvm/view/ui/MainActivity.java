package com.cale.notesmvvm.notesappmvvm.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cale.notesmvvm.notesappmvvm.view.adapter.NoteAdapter;
import com.cale.notesmvvm.notesappmvvm.service.model.Note;
import com.cale.notesmvvm.notesappmvvm.R;
import com.cale.notesmvvm.notesappmvvm.viewmodel.NoteViewMoel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private NoteViewMoel noteViewMoel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);


        noteViewMoel = ViewModelProviders.of(this).get(NoteViewMoel.class);
        noteViewMoel.getAllNotes().observe(this, new Observer<List<Note>>() {

            @Override
            public void onChanged(@Nullable List<Note> notes) {

                noteAdapter.setNotes(notes);

                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
