package com.android.interview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

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
                // TODO: Save notes
                Intent exit = new Intent();
                setResult(RESULT_OK, exit);
                finish();
            }
        });
    }
}
