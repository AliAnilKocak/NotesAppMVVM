package com.cale.notesmvvm.notesappmvvm.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.cale.notesmvvm.notesappmvvm.R;

import static android.app.Activity.RESULT_OK;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.cale.notesmvvm.notesappmvvm.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "com.cale.notesmvvm.notesappmvvm.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPTION =
            "com.cale.notesmvvm.notesappmvvm.EXTRA_DESCRIPTION";

    public static final String EXTRA_PRIORITY =
            "com.cale.notesmvvm.notesappmvvm.EXTRA_PRIORITY";

    private EditText txtTitle, txtDescription;
    private NumberPicker np_priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        np_priority = findViewById(R.id.np_priority);

        np_priority.setMinValue(1);
        np_priority.setMaxValue(10);


        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            txtTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            txtDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            np_priority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));

        } else {
            setTitle("Add Note");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveNote() {
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();
        int priority = np_priority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Lütfen notun başlığını ve açıklamasını değiştiriniz.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK, data);
        finish();

    }
}
