package org.smallbean.interview;

import org.smallbean.interview.utilities.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Interview extends Activity {
    private static final int TAKE_PHOTO = 0;
    private static final int RECORD_AUDIO = 1;
    private static final int RECORD_VIDEO = 2;
    private static final int SHOW_GALLERY = 3;
    private static final int TAKE_NOTE = 4;

    private static final int SUBJECT_DASHBOARD_VIEW = 0;
    private static final int SUBJECT_CREATE_VIEW = 1;
    private static final int SUBJECT_LIST_VIEW = 2;
    private static final int SUBJECT_DETAILS_VIEW = 3;

    private ViewFlipper flipper;
    private ListView subjectListView;

    private Data data = Data.getInstance();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        this.flipper = (ViewFlipper) findViewById(R.id.subject_views);

        // Construct list of subjects
        this.subjectListView = (ListView) findViewById(R.id.subject_list_view);
        this.populateListView(this.subjectListView);

        subjectListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {

                String subjectName = (String) ((TextView) view).getText();
                data.SetSubject(subjectName);

                TextView subjectview = (TextView) findViewById(R.id.subjectTitle);
                subjectview.setText(subjectName);

                flipper.setDisplayedChild(Interview.SUBJECT_DETAILS_VIEW);
            }
        });
    }

    private void populateListView(ListView subjectListView) {
        String[] subjects = this.data.GetSubjects();

        if (subjects == null)
            return;

        ArrayAdapter<String> subjectListAdapter = new ArrayAdapter<String>(
                this, R.layout.subject_list_item_view, subjects);
        subjectListView.setAdapter(subjectListAdapter);
    }

    public void createSubject(View view) {
        // TODO: Remove keyboard after subject creation
        EditText newSubjectField = (EditText) findViewById(R.id.subject_create_name);
        String currentSubjectName = newSubjectField.getText().toString();

        data.AddSubject(currentSubjectName);
        TextView subjectview = (TextView) findViewById(R.id.subjectTitle);
        subjectview.setText(currentSubjectName);
        data.SetSubject(currentSubjectName);

        this.populateListView(this.subjectListView);
        flipper.setDisplayedChild(Interview.SUBJECT_DETAILS_VIEW);
    }

    public void newSubject(View view) {
        flipper.setDisplayedChild(Interview.SUBJECT_CREATE_VIEW);
    }

    public void listSubjects(View view) {
        flipper.setDisplayedChild(Interview.SUBJECT_LIST_VIEW);
    }

    public void takePhoto(View view) {
        // TODO: Check for interviewTitle (see above)
        Intent intent = new Intent(this, CameraSurface.class);
        startActivityForResult(intent, TAKE_PHOTO);

    }

    public void takeNote(View view) {
        // TODO: Check for interviewTitle (see above)
        Intent intent = new Intent(this, Note.class);
        startActivityForResult(intent, TAKE_NOTE);

    }

    public void recordAudio(View view) {
        // TODO: Check for interviewTitle (see above)
        Intent intent = new Intent(this, Audio.class);
        startActivityForResult(intent, RECORD_AUDIO);
    }

    public void recordVideo(View view) {
        // TODO: Invoke video recording activity

        // Intent intent = new Intent(this, VideoCapture.class);
        // startActivityForResult(intent, RECORD_VIDEO);

        Intent intent = new Intent(this, VideoGallery.class);
        startActivityForResult(intent, RECORD_VIDEO);
    }

    public void finish(View view) {
        flipper.setDisplayedChild(Interview.SUBJECT_LIST_VIEW);
    }

    public void goHome(View view) {
        flipper.setDisplayedChild(Interview.SUBJECT_DASHBOARD_VIEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
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
