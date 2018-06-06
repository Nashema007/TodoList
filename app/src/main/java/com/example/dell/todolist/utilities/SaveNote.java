package com.example.dell.todolist.utilities;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveNote {


    public static final String FILE_EXTENSION = ".txt";

    //SAVES NOTE INTO APPS PRIVATE STORAGE
    public static boolean saveNotes(Context context, Notes note) {


        String fileName = String.valueOf(note.getDateTime()) + FILE_EXTENSION;

        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;


        try {

            fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(note);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return true;
    }

    //STATIC METHOD USED TO RETRIEVE ALL SAVED NOTE
    //SO THAT THEY POPULATE THE RECYCLERVIEW
    public static ArrayList<Notes> getAllSavedNotes(Context context) {

        ArrayList<Notes> note = new ArrayList<>();

        File filesDr = context.getFilesDir();
        ArrayList<String> noteList = new ArrayList<>();
        for (String file : filesDr.list()) {

            if (file.endsWith(FILE_EXTENSION)) {
                noteList.add(file);
            }
        }

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        for (int i = 0; i < noteList.size(); i++) {
            try {

                fileInputStream = context.openFileInput(noteList.get(i));
                objectInputStream = new ObjectInputStream(fileInputStream);
                note.add((Notes) objectInputStream.readObject());

                fileInputStream.close();
                objectInputStream.close();

            } catch (IOException | ClassNotFoundException e) {

                e.printStackTrace();
                return null;


            }
        }
        return note;

    }

    //WHEN SINGLE NOTE IS SELECT ITS TITLE AND CONTENTS ARE
    //RETRIEVED BY THIS METHOD
    public static Notes getSingleNote(Context context, String filename) {

        File file = new File(context.getFilesDir(), filename);
        Notes notes;

        if (file.exists()) {

            FileInputStream fileInputStream;
            ObjectInputStream objectInputStream;

            try {

                fileInputStream = context.openFileInput(filename);
                objectInputStream = new ObjectInputStream(fileInputStream);

                notes = (Notes) objectInputStream.readObject();

                fileInputStream.close();
                objectInputStream.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;

            }

            return notes;

        }

        return null;
    }


}
