package org.smallbean.interview


import java.io.File

import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import android.widget.AdapterView.OnItemClickListener


class CameraSurface extends Activity {	
	val CAMERA_RESULT = 1
	
	override def onCreate(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera)
        
        val camera_gallery = findViewById(R.id.photo_gallery).asInstanceOf[android.widget.Gallery]
        
	    camera_gallery.setAdapter(new ImageAdapter(this))

	    camera_gallery.setOnItemClickListener(new OnItemClickListener() {
            def onItemClick(parent:AdapterView[_], view:View, position:Int, id:Long) { 
	            Toast.makeText(CameraSurface.this, "" + position, Toast.LENGTH_SHORT).show()
	        }
	    })
                         
    }
	
	
	def goHome(view:View) {
	}
	
	def finish(view:View) {		
		this.finish()
	}
	
	def takePhoto(view:View) {		    	    
        showToast(this,Data.GetNewPhotoURL)       	               
                
        val imageFileUri = Uri.fromFile(new File(Data.GetNewPhotoURL))
        
        var intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri)
        
        startActivityForResult(intent, this.CAMERA_RESULT)               		
	}

	
    def onActivityResult(requestCode:Int, resultCode:Int, image:Intent) {
        super.onActivityResult(requestCode, resultCode, image)
                
       if(requestCode == CAMERA_RESULT){
    	    val camera_gallery = findViewById(R.id.photo_gallery).asInstanceOf[android.widget.Gallery]
    	   
    	    camera_gallery.setAdapter(new ImageAdapter(this))

   	    	camera_gallery.setOnItemClickListener(new OnItemClickListener() {
   	    		def onItemClick(parent:AdapterView[_], view:View, position:Int, id:Long) { 
   	    			Toast.makeText(CameraSurface.this, "" + position, Toast.LENGTH_SHORT).show()
   	    		}
   	    	})    	                                       	
        }
    
               
    }
    
    private def showToast(mContext:Context, text:String) {
    	Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
    }
    
    
    class ImageAdapter(c:Context) extends BaseAdapter {
        val mContext = c
        val a = obtainStyledAttributes(R.styleable.Gallery)
        val mGalleryItemBackground = a.getResourceId(
                R.styleable.Gallery_android_galleryItemBackground, 0)
        a.recycle()
	    val imagePaths:Array[String] = Data.GetPhotoURLs

	    def getCount:Int = imagePaths.length

	    def getItem(position:Int):Object = position.asInstanceOf[Object]

	    def getItemId(position:Int) = position

	    override def getView(position:Int, convertView:View, parent:ViewGroup):View = {
	        var i = new ImageView(mContext)
	        	      
	        i.setImageDrawable(downSampleImage(imagePaths(position)))
	        i.setLayoutParams(new android.widget.Gallery.LayoutParams(400, 400))
	        i.setScaleType(ImageView.ScaleType.FIT_XY)
	        i.setBackgroundResource(mGalleryItemBackground)

	        i
	    }
	    
	    private def downSampleImage(imgPath:String):BitmapDrawable = {
	    	var options = new BitmapFactory.Options()
	        options.inSampleSize = 2
	    	var bmp = BitmapFactory.decodeFile(imgPath, options)	    
	    	new BitmapDrawable(bmp)
	    }

	}
}
