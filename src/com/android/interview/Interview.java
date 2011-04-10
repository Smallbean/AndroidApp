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
}