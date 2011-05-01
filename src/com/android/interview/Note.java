package com.android.interview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Note extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        
        TextView subject_name = (TextView)findViewById(R.id.subjectname);        
        subject_name.setText("subject to set");
        
	}
      
	
}
