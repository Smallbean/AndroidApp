package com.android.interview;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.interview.utilities.Data;


public class Interview extends Activity {
    private static final int TAKE_PHOTO = 0;
    private static final int RECORD_AUDIO = 1;
    private static final int RECORD_VIDEO = 2;
    private static final int SHOW_GALLERY = 3;

    private static final int SUBJECT_DASHBOARD_VIEW = 0;
    private static final int SUBJECT_CREATE_VIEW = 1;
    private static final int SUBJECT_LIST_VIEW = 2;
    private static final int SUBJECT_DETAILS_VIEW = 3;

    private ViewFlipper flipper;
    
    private Data data = Data.getInstance();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
                    
        setContentView(R.layout.dashboard);

        this.flipper = (ViewFlipper) findViewById(R.id.subject_views);

        // Setup dash board buttons
        Button subjectCreateButton = (Button) findViewById(R.id.subject_create_button);
        subjectCreateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.SUBJECT_CREATE_VIEW);
            }
        });

        Button subjectListButton = (Button) findViewById(R.id.subject_list_button);
        subjectListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.SUBJECT_LIST_VIEW);
            }
        });

        // Setup subject creation buttons
        Button subjectCreateSaveButton = (Button) findViewById(R.id.subject_create_save_button);
        subjectCreateSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText newSubjectField = (EditText) findViewById(R.id.subject_create_name);
                String currentSubjectName = newSubjectField.getText()
                        .toString();

                createSubject(currentSubjectName);

                // Update dropdown list
                Spinner spinner = (Spinner) findViewById(R.id.subject_list_dropdown);
                populateSpinner(spinner);
                
                // Update the subject title in the subject detail view of the current subject
                TextView subjectview = (TextView) findViewById(R.id.subjectname);
                subjectview.setText(currentSubjectName);
                
                data.SetSubject(currentSubjectName);

                flipper.setDisplayedChild(Interview.SUBJECT_DETAILS_VIEW);
            }
        });

        Button subjectCreateBackButton = (Button) findViewById(R.id.subject_create_back_button);
        subjectCreateBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.SUBJECT_DASHBOARD_VIEW);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.subject_list_dropdown);
        populateSpinner(spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                String subjectName = parent.getItemAtPosition(position)
                        .toString();
                data.SetSubject(subjectName);
                
                
                // Update the subject title in the subject detail view of the current subject
                TextView subjectview = (TextView) findViewById(R.id.subjectname);
                subjectview.setText(subjectName);
                
                data.SetSubject(subjectName);
            }

            public void onNothingSelected(AdapterView parent) {
                // Do nothing
            }
        });

        Button subjectListViewButton = (Button) findViewById(R.id.subject_list_view_button);
        subjectListViewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.SUBJECT_DETAILS_VIEW);
            }
        });

        Button subjectListBackButton = (Button) findViewById(R.id.subject_list_back_button);
        subjectListBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.SUBJECT_DASHBOARD_VIEW);
            }
        });

        // Setup subject view buttons
        Button subjectViewBackButton = (Button) findViewById(R.id.subject_dashboard_button);
        subjectViewBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.SUBJECT_DASHBOARD_VIEW);
            }
        });
    }

    public void populateSpinner(Spinner spinner) {
    	String[] subjects = this.data.GetSubjects();
    	
    	if(subjects==null) return;
    	
        // Setup subject listing buttons
        ArrayAdapter<String> subjectListAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,                
        	    subjects);
        
        spinner.setAdapter(subjectListAdapter);
    }

    public void createSubject(String currentSubjectName) {    	
    	data.AddSubject(currentSubjectName);    
    }

    public void takePhoto(View view) {
        // TODO: Check for interviewTitle (see above)
        Intent intent = new Intent(this, CameraSurface.class);
        startActivityForResult(intent, TAKE_PHOTO);
        
        
    }
    
    public void showGallery(View view){
    	
    	Intent intent = new Intent(this, Gallery.class);
    	startActivityForResult(intent, SHOW_GALLERY);
    }

    public void recordAudio(View view) {
        // TODO: Check for interviewTitle (see above)
        Intent intent = new Intent(this, AudioRecorder.class);
        startActivityForResult(intent, RECORD_AUDIO);
    }

    public void recordVideo(View view) {
        // TODO: Invoke video recording activity
    	Intent intent = new Intent(this, VideoCapture.class);
        startActivityForResult(intent, RECORD_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle extras = null;
        if (intent != null) {
            extras = intent.getExtras();
        }
        // Bundle extras = intent.getExtras();
        switch (requestCode) {
            case TAKE_PHOTO:
                break;
            case RECORD_AUDIO:
                break;
            case RECORD_VIDEO:
                break;
        }
    }
}
