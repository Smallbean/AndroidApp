package org.smallbean.interview

import java.io.IOException

import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.Window
import android.view.WindowManager
import android.media.MediaRecorder
import android.view.View
import android.view.SurfaceHolder.Callback
import android.view.View.OnClickListener
import android.widget.Toast


class VideoCapture extends Activity with OnClickListener with Callback 
{
    var recording = false
	var recorder:MediaRecorder = null
	var holder:SurfaceHolder = null
	var imageFilePath = ""
	
	override def onCreate(savedInstanceState:Bundle) 
	{
        super.onCreate(savedInstanceState)
        
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        
        setContentView(R.layout.cameraview)
        
        imageFilePath = Data.GetNewVideoURL
        Log.d("VideoPath", imageFilePath)
        
        
        initRecorder
        val cameraView = findViewById(R.id.surface).asInstanceOf[SurfaceView]
        holder = cameraView.getHolder()
        holder.addCallback(this)
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)

        cameraView.setClickable(true)
        cameraView.setOnClickListener(this)
         
    }

	private def initRecorder
	{
		recorder = new MediaRecorder()
		
		recorder.setAudioSource(recorder.AudioSource.DEFAULT)
		recorder.setVideoSource(recorder.VideoSource.DEFAULT)
		recorder.setOutputFormat(recorder.OutputFormat.MPEG_4)
		recorder.setAudioEncoder(recorder.AudioEncoder.DEFAULT)
		recorder.setVideoEncoder(recorder.VideoEncoder.DEFAULT)
		recorder.setOutputFile(imageFilePath)
		
		recorder.setMaxDuration(50000)
		recorder.setMaxFileSize(5000000)
	}
	
	override def onClick(v:View)
	{
		if(recording)
		{
			
			recorder.stop()
			showToast(this,"Recording stopped")
			recording = false
			finish()
		}
		else
		{
			recording = true
			recorder.start()
			
			showToast(this,"Recording started")
		}
	}

	override def surfaceChanged(holder:SurfaceHolder, format:Int, width:Int, height:Int) {
		// TODO Auto-generated method stub

	}

	private def prepareRecorder
	{
		
		recorder.setPreviewDisplay(holder.getSurface())
		try
		{
			
			recorder.prepare()
		}
		catch
		{
			case _ => finish()
		}
	}
	
	override def surfaceCreated(holder:SurfaceHolder) 
	{		
		prepareRecorder
	}

	override def surfaceDestroyed(holder:SurfaceHolder)
	{
		if(recording)
		{
			recorder.stop()
			recording = false
		}
		recorder.release()
		finish()
	}
	
	  
  private def showToast(mContext:Context, text:String) {
  	Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
  }

}
