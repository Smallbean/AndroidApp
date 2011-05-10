package org.smallbean.interview


import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget._
import android.widget.AdapterView.OnItemClickListener

class Interview extends Activity {
    val TAKE_PHOTO = 0
    val RECORD_AUDIO = 1
    val RECORD_VIDEO = 2
    val SHOW_GALLERY = 3
    val TAKE_NOTE = 4

    val SUBJECT_DASHBOARD_VIEW = 0
    val SUBJECT_CREATE_VIEW = 1
    val SUBJECT_LIST_VIEW = 2
    val SUBJECT_DETAILS_VIEW = 3

    def flipper:ViewFlipper= findViewById(R.id.subject_views).asInstanceOf[ViewFlipper]
    
    /** Called when the activity is first created. */
    override def onCreate(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.dashboard)

        // Setup dash board buttons
        val subjectCreateButton = findViewById(R.id.subject_create_button).asInstanceOf[Button]
        subjectCreateButton.setOnClickListener(new View.OnClickListener() {
            def onClick(view:View) {
                flipper.setDisplayedChild(SUBJECT_CREATE_VIEW)
            }
        })

        val subjectListButton = findViewById(R.id.subject_list_button).asInstanceOf[Button]
        subjectListButton.setOnClickListener(new View.OnClickListener() {
            def onClick(view:View) {
                val subjectListView = findViewById(R.id.subject_list_view).asInstanceOf[ListView]
                populateListView(subjectListView)
                flipper.setDisplayedChild(SUBJECT_LIST_VIEW)
            }
        })

        // Setup subject creation buttons
        val subjectCreateSaveButton = findViewById(R.id.subject_create_save_button).asInstanceOf[Button]
        subjectCreateSaveButton.setOnClickListener(new View.OnClickListener() {
            def onClick(view:View) {
                val newSubjectField = findViewById(R.id.subject_create_name).asInstanceOf[EditText]
                val currentSubjectName = newSubjectField.getText()
                        .toString()

                createSubject(currentSubjectName)
                val subjectview = findViewById(R.id.subjectname).asInstanceOf[TextView]
                subjectview.setText(currentSubjectName)
                
                Data.SetSubject(currentSubjectName)

                flipper.setDisplayedChild(SUBJECT_DETAILS_VIEW)
            }
        })

        val subjectCreateBackButton = findViewById(R.id.subject_create_back_button).asInstanceOf[Button]
        subjectCreateBackButton.setOnClickListener(new View.OnClickListener() {
            def onClick(view:View) {
                flipper.setDisplayedChild(SUBJECT_DASHBOARD_VIEW)
            }
        })
        
        var subjectListView = findViewById(R.id.subject_list_view).asInstanceOf[ListView]
        populateListView(subjectListView)
        subjectListView.setOnItemClickListener(new OnItemClickListener() {
            def onItemClick(parent:AdapterView[_], view:View, position:Int, id:Long) {            
              
              val subjectName = view.asInstanceOf[TextView].getText().asInstanceOf[String]
              Data.SetSubject(subjectName)
              
              val subjectview = findViewById(R.id.subjectname).asInstanceOf[TextView]
              subjectview.setText(subjectName)
              
              flipper.setDisplayedChild(SUBJECT_DETAILS_VIEW)
            }
        })    

    }    
    
    override protected def onStart() {
    	super.onStart()
        val subjectListView = findViewById(R.id.subject_list_view).asInstanceOf[ListView]
        populateListView(subjectListView)
    }
    
	override protected def onResume() {
		super.onResume()
        val subjectListView = findViewById(R.id.subject_list_view).asInstanceOf[ListView]
        populateListView(subjectListView)
	}


    
    def populateListView(subjectListView:ListView) 
    {
    	val subjects = Data.GetSubjects
    	
        // Setup subject listing buttons
        var subjectListAdapter = new ArrayAdapter[String](
                this, R.layout.subject_list_item_view,                
        	    subjects)
        
        subjectListView.setAdapter(subjectListAdapter)
    }

    def createSubject(currentSubjectName:String) {    	
    	Data.AddSubject(currentSubjectName)    
    }

    def takePhoto(view:View) {
        // TODO: Check for interviewTitle (see above)
        val intent = new Intent(this, classOf[CameraSurface])
        startActivityForResult(intent, TAKE_PHOTO)
        
        
    }
    
    def takeNote(view:View) {
        // TODO: Check for interviewTitle (see above)
        val intent = new Intent(this, classOf[Note])
        startActivityForResult(intent, TAKE_NOTE)
        
        
    }
    
    def showGallery(view:View){
    	
    	val intent = new Intent(this, classOf[Gallery])
    	startActivityForResult(intent, SHOW_GALLERY)
    }

    def recordAudio(view:View) {
        // TODO: Check for interviewTitle (see above)
        val intent = new Intent(this, classOf[Audio])
        startActivityForResult(intent, RECORD_AUDIO)
    }

    def recordVideo(view:View) {
        // TODO: Invoke video recording activity    	
    	val intent = new Intent(this, classOf[VideoGallery])
        startActivityForResult(intent, RECORD_VIDEO)
    }
    
	def finish(view:View) {		
        flipper.setDisplayedChild(SUBJECT_LIST_VIEW)	
    }
	
	def goHome(view:View) {
		flipper.setDisplayedChild(SUBJECT_DASHBOARD_VIEW) 
	}

    override protected def onActivityResult(requestCode:Int, resultCode:Int, intent:Intent) {
        super.onActivityResult(requestCode, resultCode, intent)
    }
}
