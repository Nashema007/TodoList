package com.example.dell.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.todolist.R;
import com.example.dell.todolist.utilities.Notes;
import com.example.dell.todolist.utilities.SaveNote;

public class EditNotesActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private Button save;
    private long time;
    private Notes loadNotes;
    private String noteFileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        title = findViewById(R.id.notesTitle);
        content = findViewById(R.id.content);
        save = findViewById(R.id.saveBtn);
        time = System.currentTimeMillis();


        //REOPENS THE NOTE WITH ITS TITLE AND CONTENT FROM PREVIOUS SESSION/FROM WHEN IT WAS LAST EDITED
        noteFileName = getIntent().getStringExtra("FILENAME");

        if (noteFileName != null && !noteFileName.isEmpty()) {

            loadNotes = SaveNote.getSingleNote(this, noteFileName);

            if (loadNotes != null) {
                title.setText(loadNotes.getTitle());
                content.setText(loadNotes.getContent());

            }

        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //CHECK TO MAKE SURE NOTE TITLE AND CONTENT ARE NOTE EMPTY
                if ((title.getText().toString().isEmpty())) {

                    title.setError("Please enter title first");

                } else if ((content.getText().toString().isEmpty())) {
                    content.setError("Please enter your note");
                } else {

                    Intent intent = new Intent(EditNotesActivity.this, MainActivity.class);
                    startActivity(intent);
                    saveNote();
                }
            }
        });


    }


    public void saveNote() {

        Notes note;

        //CHECKS IF THE NOTE IS ALREADY AVAILABLE
        if (loadNotes == null) {

            //IF NOTE IS NOT AVAILABLE IT SAVES NOTE
            note = new Notes(content.getText().toString(), title.getText().toString(), time);
            note.setContent(content.getText().toString());
        } else {
            //ELSE NOTE IS AVAILABLE SAVE NOTE AND KEEP THE NOTES SAVED TITLE(TIME IS THE UNIQUE KEY USED TO DIFFERENTIATE EACH NOTE)
            note = new Notes(content.getText().toString(), title.getText().toString(), loadNotes.getDateTime());
            note.setContent(content.getText().toString());
        }

        if (SaveNote.saveNotes(this, note)) {

            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
            finish();

        } else {

            Toast.makeText(this, "Not saved", Toast.LENGTH_LONG).show();
        }

        finish();
    }
}
