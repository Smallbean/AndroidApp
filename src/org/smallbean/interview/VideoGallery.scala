package org.smallbean.interview

import java.io.File

import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.AdapterView.OnItemClickListener


class VideoGallery extends Activity {

    var videoFilePaths:Array[String] = null
    var videoFile:File = null   
        
    override def onCreate(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video)
        
        val video_gallery = findViewById(R.id.video_gallery).asInstanceOf[android.widget.Gallery]
        
        video_gallery.setAdapter(new VideoAdapter(this))

        video_gallery.setOnItemClickListener(new OnItemClickListener() {
            def onItemClick(parent:AdapterView[_], view:View, position:Int, id:Long) {  
	        	//videoFile = new File(videoFilePaths[position])
	        	String videoPath = videoFilePaths[position]
	        	Intent intent = new Intent(android.content.Intent.ACTION_VIEW) 
	            Uri data = Uri.parse(videoPath) 
	            intent.setDataAndType(data,"video/mp4") 
	            startActivity(intent)
	        }
	    })
	    
    }
    
    def recordVideo(view:View) {
        val intent = new Intent(this, classOf[VideoCapture])
        startActivityForResult(intent, 0)
    	
    }
      
     
    
    class VideoAdapter extends BaseAdapter {
	    var mGalleryItemBackground:Int = null
	    var mContext:Context = null

	    private def getVideoPaths
	    {
	    	videoFilePaths = Data.GetVideoURLs
	    }

	    def VideoAdapter(c:Context) {
	    	getVideoPaths()
	    	
	        mContext = c
	        val a = obtainStyledAttributes(R.styleable.Gallery)
	        mGalleryItemBackground = a.getResourceId(
	                R.styleable.Gallery_android_galleryItemBackground, 0)
	        a.recycle()
	    }

	    def getCount:Int = videoFilePaths.length

	    def getItem(position:Int):Object = position

	    def getItemId(position:Int) = position

	    override def getView(position:Int, convertView:View, parent:ViewGroup): View = {
	    	
	    	val videoThumbNail = new ImageView(mContext)
	    	val mBitmap = BitmapFactory.decodeResource(getResources(),
	    			   R.drawable.sub_vid_wotext)
	    	
	    	videoThumbNail.setLayoutParams(new android.widget.Gallery.LayoutParams(250, 250))	    	
	    	videoThumbNail.setBackgroundResource(mGalleryItemBackground)
	    	videoThumbNail.setImageBitmap(mBitmap)
	    	
	        videoThumbNail
	    }	   	   

	}    
	
}
