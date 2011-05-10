package org.smallbean.interview

import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup


class Gallery extends Activity {
	
	override def onCreate(savedInstanceState:Bundle) {
	    super.onCreate(savedInstanceState)
	    setContentView(R.layout.image_gallery)
	    
	    val g = findViewById(R.id.gallery_layout).asInstanceOf[android.widget.Gallery]	    	   
	    
	    g.setAdapter(new ImageAdapter(this))

	    g.setOnItemClickListener(new OnItemClickListener() {
            def onItemClick(parent:AdapterView[_], view:View, position:Int, id:Long) { 
	            Toast.makeText(Gallery.this, "" + position, Toast.LENGTH_SHORT).show()
	        }
	    })
	}
	
	
	class ImageAdapter extends BaseAdapter {
	    var imagePaths:Array[String] = Data.GetPhotoURLs()	    
	    var mGalleryItemBackground:Int = null
	    var mContext:Context = null

	    def ImageAdapter(c:Context) {
	        mContext = c
	        val a = obtainStyledAttributes(R.styleable.Gallery)
	        mGalleryItemBackground = a.getResourceId(
	                R.styleable.Gallery_android_galleryItemBackground, 0)
	        a.recycle()
	    }

	    def getCount:Int = imagePaths.length

	    def getItem(position:Int):Object = position

	    def getItemId(position:Int) = position

	    override def getView(position:Int, convertView:View, parent:ViewGroup):View = {
	        val i = new ImageView(mContext)
	        
	        i.setImageDrawable(downSampleImage(imagePaths[position]))
	        i.setLayoutParams(new android.widget.Gallery.LayoutParams(150, 100))
	        i.setScaleType(ImageView.ScaleType.FIT_XY)
	        i.setBackgroundResource(mGalleryItemBackground)

	        i
	    }
	    
	    private def downSampleImage(imgPath:String):BitmapDrawable = {
	    	val options = new BitmapFactory.Options()
	        options.inSampleSize = 2
	    	val bmp = BitmapFactory.decodeFile(imgPath, options)
	    	
	    	val rbmd = new BitmapDrawable(bmp)
	    	
	    	return rbmd
	    }

	}
}
