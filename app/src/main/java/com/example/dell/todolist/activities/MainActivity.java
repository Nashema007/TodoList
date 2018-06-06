package com.example.dell.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.todolist.R;
import com.example.dell.todolist.adapters.NotesAdapter;
import com.example.dell.todolist.utilities.Notes;
import com.example.dell.todolist.utilities.SaveNote;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private ImageView submit;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NotesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.EditImageView);
        recyclerView = findViewById(R.id.NotesRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNotesActivity.class);
                startActivity(intent);
            }
        });


    }




    @Override
    protected void onResume() {
        super.onResume();


//RETRIEVES SAVED NOTES FROM APPS PRIVATE STORAGE AFTER NOTE HAS BEEN SAVED AND POPULATES RECYCLERVIEW
        ArrayList<Notes> notes = SaveNote.getAllSavedNotes(this);

        if (notes == null || notes.size() == 0) {
            Toast.makeText(this, "no saved notes", Toast.LENGTH_LONG).show();
        }
        adapter = new NotesAdapter(this, notes, R.layout.note_item);
        recyclerView.setAdapter(adapter);


    }
}
