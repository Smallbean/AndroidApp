package com.android.interview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Interview extends Activity {
    private static final int TAKE_NOTES = 0;
    private static final int TAKE_PHOTO = 1;
    private static final int RECORD_AUDIO = 2;
    private static final int RECORD_VIDEO = 3;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<CharSequence> listAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.interview_list,
            android.R.layout.simple_spinner_item
        );
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        Spinner spinner = (Spinner)findViewById(R.id.archive_dropdown);
        spinner.setAdapter(listAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context context = parent.getContext();
                String message = "TODO: Show " + parent.getItemAtPosition(position).toString() + "'s interview details";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
            
            public void onNothingSelected(AdapterView parent) {
                // Do nothing
            }
        });
    }
    
    public void takeNotes(View view) {
        EditText interviewTitle = (EditText)findViewById(R.id.new_interview_title);
        
        if (interviewTitle.length() > 0) {
            Intent takeNotes = new Intent(this, Notepad.class);
            // TODO: Bundle up interviewTitle.getText().toString()
            startActivityForResult(takeNotes, TAKE_NOTES);
        } else {
            Toast.makeText(this.getApplicationContext(), "Please enter a title.", Toast.LENGTH_SHORT).show();
        }
    }
    
    public void takePhoto(View view) {
        // TODO: Check for interviewTitle (see above)
    	Intent intent = new Intent(this, CameraSurface.class);    
    	startActivityForResult(intent, TAKE_PHOTO);
    }
    
    public void recordAudio(View view) {
        // TODO: Invoke audio recording activity
    }
    
    public void recordVideo(View view) {
        // TODO: Invoke video recording activity
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Bundle extras = intent.getExtras();
        switch(requestCode) {
            case TAKE_NOTES:
                break;
            case TAKE_PHOTO:
                break;
            case RECORD_AUDIO:
                break;
            case RECORD_VIDEO:
                break;
        }
    }
}