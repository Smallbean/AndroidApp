package org.smallbean.interview

import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.os.Bundle
import android.widget._

class Note extends Activity {
	
	override def onCreate(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes)
        

        var note = findViewById(R.id.note_text).asInstanceOf[EditText]
        note.setText(Data.GetNote(), EditText.BufferType.EDITABLE)

        // Setup dash board buttons
        var saveNoteButton = findViewById(R.id.save).asInstanceOf[Button]
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	EditText note = (EditText) findViewById(R.id.note_text)
            	Data.SetNote(note.getText().toString())
            	Toast.makeText(view.getContext(), "Text Saved", Toast.LENGTH_SHORT).show()                
            }
        })
        
        
	}
      
	
}
