package org.smallbean.interview

import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.os.Bundle
import android.widget._
import android.widget.AdapterView.OnItemClickListener
import android.view.View
import android.widget.EditText

class Note extends Activity {
	
	override def onCreate(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes)
        

        var note = findViewById(R.id.note_text).asInstanceOf[EditText]
        note.setText(Data.GetNote)

        // Setup dash board buttons
        var saveNoteButton = findViewById(R.id.save).asInstanceOf[Button]
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            def onClick(view:View) {
            	var note = findViewById(R.id.note_text).asInstanceOf[EditText]
            	Data.SetNote(note.getText().toString())
            	Toast.makeText(view.getContext(), "Text Saved", Toast.LENGTH_SHORT).show()                
            }
        })
        
        
	}
      
	
}
