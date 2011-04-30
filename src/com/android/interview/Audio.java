package com.android.interview;


import java.io.IOException;

import com.android.interview.CameraSurface.ImageAdapter;
import com.android.interview.utilities.Data;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Audio extends Activity {
      
    private Data data = Data.getInstance();

   
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        android.widget.Gallery audio_gallery = (android.widget.Gallery)findViewById(R.id.audio_gallery);
        
        audio_gallery.setAdapter(new AudioAdapter(this));

        audio_gallery.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView parent, View v, int position, long id) {
	        	//do nothing for now on clicks
	        }
	    });
	    
    }
      
    public class AudioAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	    private String[] audioFilePaths;
	    private Context mContext;

	    private void getDrawables()
	    {
	    	String[] audioFiles = data.GetAudioURLs();
	    	audioFilePaths = new String[audioFiles.length];
	    	
	    	for(int i=0;i<audioFiles.length;i++)
	    	{		    	   			    
		    	audioFilePaths[i] = audioFiles[i];
	    	}
	    }
	    
	    

	    public AudioAdapter(Context c) {
	    	getDrawables();
	    	
	        mContext = c;
	        TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
	        mGalleryItemBackground = a.getResourceId(
	                R.styleable.Gallery_android_galleryItemBackground, 0);
	        a.recycle();
	    }

	    public int getCount() {
	        return audioFilePaths.length;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    @Override	
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	
	    	ListView list = new ListView(mContext);
	        
	    
	        	

	        return list;
	    }
	    
	    

	}
}
