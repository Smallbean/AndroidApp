package com.android.interview;

import java.io.File;
import com.android.interview.utilities.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;


public class VideoGallery extends Activity{

    
    private Data data = Data.getInstance();
    private String[] videoFilePaths;
    
    
    File videoFile;
   
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        
        android.widget.Gallery video_gallery = (android.widget.Gallery)findViewById(R.id.video_gallery);
        
        video_gallery.setAdapter(new VideoAdapter(this));

        video_gallery.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        //	videoFile = new File(videoFilePaths[position]);
	        	
	        }
	    });
	    
    }
    
    public void recordVideo(View view) {
        Intent intent = new Intent(this, VideoCapture.class);
        startActivityForResult(intent, 0);
    	
    }
      
     
    
    public class VideoAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	    
	    private Context mContext;

	    private void getVideoPaths()
	    {
	    	String[] videoFiles = data.GetVideoURLs();
	    	videoFilePaths = new String[videoFiles.length];
	    	
	    	for(int i=0;i<videoFiles.length;i++)
	    	{		    	   			    
	    		videoFilePaths[i] = videoFiles[i];
	    	}
	    }
	    
	    

	    public VideoAdapter(Context c) {
	    	getVideoPaths();
	    	
	        mContext = c;
	        TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
	        mGalleryItemBackground = a.getResourceId(
	                R.styleable.Gallery_android_galleryItemBackground, 0);
	        a.recycle();
	    }

	    public int getCount() {
	        return videoFilePaths.length;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    @Override	
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	
	    	ImageView videoThumbNail = new ImageView(mContext);
	    	Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),
	    			   R.drawable.sub_vid_wotext);
	    	
	    	videoThumbNail.setLayoutParams(new android.widget.Gallery.LayoutParams(250, 250));	    	
	    	videoThumbNail.setBackgroundResource(mGalleryItemBackground);
	    	videoThumbNail.setImageBitmap(mBitmap);
	    	
	        return videoThumbNail;
	    }	   	   

	}    
	
}
