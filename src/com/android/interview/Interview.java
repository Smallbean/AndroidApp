package com.android.interview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Interview extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void takePhoto(View view)
    {
    	Intent intent= new Intent(this, Camera.class);
    	startActivity(intent);
    }
    
    public void takeNotes(View view) {
        // TODO: Call Notepad activity with intent
        Intent takeNotes = new Intent(this, Notepad.class);
        startActivityForResult(takeNotes, 0);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // TODO: Implement behavior for completion of video/photo/text recording activity
    }
}