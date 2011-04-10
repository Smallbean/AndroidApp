package com.android.interview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Interview extends Activity {
    private static final int TAKE_NOTES = 0;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void takePhoto(View view)
    {
    	Intent intent= new Intent(this, Camera.class);    
    	startActivityForResult(intent, 0);
    }
    
    public void takeNotes(View view) {
        // TODO: Call Notepad activity with intent
        Intent takeNotes = new Intent(this, Notepad.class);
        startActivityForResult(takeNotes, TAKE_NOTES);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // TODO: Implement behavior for completion of video/photo/text recording activity
    }
}