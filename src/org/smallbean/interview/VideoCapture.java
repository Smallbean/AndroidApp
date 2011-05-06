package org.smallbean.interview;

import java.io.IOException;

import org.smallbean.interview.utilities.Data;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.media.MediaRecorder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class VideoCapture extends Activity implements OnClickListener, Callback 
{
	private String imageFilePath;
	
    private boolean recording = false;
	private MediaRecorder recorder;
	private SurfaceHolder holder;

    private Data data = Data.getInstance();
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        setContentView(R.layout.cameraview);
        
        imageFilePath = data.GetNewVideoURL();
        Log.d("VideoPath", imageFilePath);
        
        
        initRecorder();
        SurfaceView cameraView = (SurfaceView) findViewById(R.id.surface);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);
         
    }

	private void initRecorder()
	{
		recorder = new MediaRecorder();
		
		recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
		recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
		
		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
		recorder.setOutputFile(imageFilePath);
		
		recorder.setMaxDuration(50000);
		recorder.setMaxFileSize(5000000);
	}
	
	@Override
	public void onClick(View v)
	{
		if(recording)
		{
			
			recorder.stop();
			showToast(this,"Recording stopped");
			recording = false;
			finish();
			//initRecorder();
			//prepareRecorder();
		}
		else
		{
			recording = true;
			recorder.start();
			
			showToast(this,"Recording started");
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	private void prepareRecorder()
	{
		
		recorder.setPreviewDisplay(holder.getSurface());
		try
		{
			
			recorder.prepare();
		}
		catch(IllegalStateException e)
		{
			
			e.printStackTrace();
			finish();
		}
		catch(IOException e)
		{
		    e.printStackTrace();
		    finish();
		}
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		
		prepareRecorder();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		if(recording)
		{
			recorder.stop();
			recording = false;
		}
		recorder.release();
		finish();
	}
	
	

  	public void captureVideo(View view) 
  	{		    	    
          showToast(this,imageFilePath);       	               
                  
  		
  	}

  
  private void showToast(Context mContext, String text) {
  	Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
  }

}
