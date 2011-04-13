package com.android.interview;


import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Notepad extends Activity {
    private EditText notes;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notepad);
        setTitle(R.string.notepad_title);
        
        notes = (EditText)findViewById(R.id.notepad_notes);
        Button saveButton = (Button)findViewById(R.id.notepad_save);
        
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    saveNotes(notes);
                } catch (IOException e) {
                    setResult(RESULT_CANCELED, new Intent());
                    finish();
                }
            }
        });
    }
    
    private void saveNotes(EditText notes) throws IOException {
        // TODO: Prompt for text file title
        String fileName = "InterviewNotes.txt";
        
        FileOutputStream noteFile = openFileOutput(fileName, Context.MODE_PRIVATE);
        noteFile.write(notes.getText().toString().getBytes());
        noteFile.close();
        
        Intent takeNotes = new Intent();
        setResult(RESULT_OK, takeNotes);
        finish();
    }
}
