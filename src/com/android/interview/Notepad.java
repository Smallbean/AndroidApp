package com.android.interview;

import android.app.Activity;
import android.os.Bundle;

public class Notepad extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notepad);
    }
}
