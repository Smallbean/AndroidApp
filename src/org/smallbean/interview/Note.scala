package org.smallbean.interview;

import org.smallbean.interview.utilities.Data;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Note extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        

        EditText note = (EditText) findViewById(R.id.note_text);
        note.setText(Data.GetNote(), EditText.BufferType.EDITABLE);

        // Setup dash board buttons
        Button saveNoteButton = (Button) findViewById(R.id.save);
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	EditText note = (EditText) findViewById(R.id.note_text);
            	Data.SetNote(note.getText().toString());
            	Toast.makeText(view.getContext(), "Text Saved", Toast.LENGTH_SHORT).show();                
            }
        });
        
        
	}
      
	
}
