package com.android.interview;

import com.android.interview.utilities.Data;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Note extends Activity {
	
	
    private Data data = Data.getInstance();

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        

        EditText note = (EditText) findViewById(R.id.note_text);
        note.setText(data.GetNote(), EditText.BufferType.EDITABLE);

        // Setup dash board buttons
        Button saveNoteButton = (Button) findViewById(R.id.save);
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	EditText note = (EditText) findViewById(R.id.note_text);
                data.SetNote(note.getText().toString());
            }
        });
        
        
	}
      
	
}
