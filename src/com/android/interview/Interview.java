package com.android.interview;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class Interview extends Activity {
    private static final int TAKE_PHOTO = 0;
    private static final int RECORD_AUDIO = 1;
    private static final int RECORD_VIDEO = 2;

    private static final int PROJECT_DASHBOARD_VIEW = 0;
    private static final int PROJECT_CREATE_VIEW = 1;
    private static final int PROJECT_LIST_VIEW = 2;
    private static final int PROJECT_DETAILS_VIEW = 3;

    private ViewFlipper flipper;
    private File currentProjectDirectory = null;
    private File baseDirectory = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        this.baseDirectory = getFilesDir();
        this.flipper = (ViewFlipper)findViewById(R.id.project_views);

        // Setup dashboard buttons
        Button projectCreateButton = (Button)findViewById(R.id.project_create_button);
        projectCreateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.PROJECT_CREATE_VIEW);
            }
        });
        
        Button projectListButton = (Button)findViewById(R.id.project_list_button);
        projectListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.PROJECT_LIST_VIEW);
            }
        });

        // Setup project creation buttons
        Button projectCreateSaveButton = (Button)findViewById(R.id.project_create_save_button);
        projectCreateSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText newProjectField = (EditText)findViewById(R.id.project_create_name);
                String currentProjectName = newProjectField.getText().toString();
                createProject(currentProjectName);
                
                flipper.setDisplayedChild(Interview.PROJECT_DETAILS_VIEW);
            }
        });
        
        Button projectCreateBackButton = (Button)findViewById(R.id.project_create_back_button);
        projectCreateBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.PROJECT_DASHBOARD_VIEW);
            }
        });
        
        // Setup project listing buttons
        // TODO: Use the following pending data
        ArrayAdapter<String> projectListAdapter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            this.baseDirectory.list()
        );
        /*ArrayAdapter<CharSequence> projectListAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.dummy_project_list,
                android.R.layout.simple_spinner_item
        );*/
        
        Spinner spinner = (Spinner)findViewById(R.id.project_list_dropdown);
        spinner.setAdapter(projectListAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String projectName = parent.getItemAtPosition(position).toString();
                currentProjectDirectory = new File(baseDirectory.getAbsolutePath() + File.separator + projectName);
            }
            
            public void onNothingSelected(AdapterView parent) {
                // Do nothing
            }
        });

        Button projectListViewButton = (Button)findViewById(R.id.project_list_view_button);
        projectListViewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.PROJECT_DETAILS_VIEW);
            }
        });
        
        Button projectViewBackButton = (Button)findViewById(R.id.project_dashboard_button);
        projectViewBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flipper.setDisplayedChild(Interview.PROJECT_DASHBOARD_VIEW);
            }
        });
    }

    public void createProject(String currentProjectName) {
        String projectDirectory = this.baseDirectory.getAbsolutePath() + File.separator + currentProjectName;
        this.currentProjectDirectory = new File(projectDirectory);
        
        this.currentProjectDirectory.mkdirs();

        String message = "Project " + currentProjectName + " created!";
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void takePhoto(View view) {
        // TODO: Check for interviewTitle (see above)
    	Intent intent = new Intent(this, CameraSurface.class);    
    	startActivityForResult(intent, TAKE_PHOTO);
    }

    public void recordAudio(View view) {
        // TODO: Check for interviewTitle (see above)
        Intent intent = new Intent(this, Audio.class);    
        startActivityForResult(intent, RECORD_AUDIO);
    }

    public void recordVideo(View view) {
        // TODO: Invoke video recording activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle extras;
        if(intent!=null){
        	extras = intent.getExtras();
        }
        // Bundle extras = intent.getExtras();
        switch(requestCode) {
            case TAKE_PHOTO:
                break;
            case RECORD_AUDIO:
                break;
            case RECORD_VIDEO:
                break;
        }
    }
}
