package com.android.interview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    }
    
    public void takeNotes(View view) {
        Intent takeNotes = new Intent(this, Notepad.class);
        startActivityForResult(takeNotes, TAKE_NOTES);
    }
    
    public void takePhoto(View view) {
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