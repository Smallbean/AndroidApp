package com.android.interview;


import java.io.File;

import com.android.interview.Gallery.ImageAdapter;
import com.android.interview.utilities.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class CameraSurface extends Activity {	
	private int CAMERA_RESULT = 1;
	private Data data = Data.getInstance();
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        
        android.widget.Gallery camera_gallery = (android.widget.Gallery)findViewById(R.id.photo_gallery);
        
	    camera_gallery.setAdapter(new ImageAdapter(this));

	    camera_gallery.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView parent, View v, int position, long id) {
	            Toast.makeText(CameraSurface.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
                         
    }
	
	
	public void takePhoto(View view) {		    	    
        showToast(this,data.GetNewPhotoURL());       	               
                
        Uri imageFileUri = Uri.fromFile(new File(data.GetNewPhotoURL()));
        
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
        
        startActivityForResult(intent, this.CAMERA_RESULT);               		
	}

	
    protected void onActivityResult(int requestCode, int resultCode, Intent image) {
        super.onActivityResult(requestCode, resultCode, image);
                
       if(requestCode == this.CAMERA_RESULT){
    	    android.widget.Gallery camera_gallery = (android.widget.Gallery)findViewById(R.id.photo_gallery);
    	   
    	    camera_gallery.setAdapter(new ImageAdapter(this));

   	    	camera_gallery.setOnItemClickListener(new OnItemClickListener() {
   	    		public void onItemClick(AdapterView parent, View v, int position, long id) {
   	    			Toast.makeText(CameraSurface.this, "" + position, Toast.LENGTH_SHORT).show();
   	    		}
   	    	});    	                
    //        Intent intent = new Intent(this, Gallery.class);
    //        startActivityForResult(intent, 0);                    	
        }
    
               
    }
    
    private void showToast(Context mContext, String text) {
    	Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
    
    
    public class ImageAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	    private String[] imagePaths;
	    private Context mContext;

	    private void getDrawables()
	    {
	    	String[] images = data.GetPhotoURLs();
	    	imagePaths = new String[images.length];
	    	
	    	for(int i=0;i<images.length;i++)
	    	{
		    	DrawableManager dm = new DrawableManager();	    			    	
		    	imagePaths[i] = images[i];
	    	}
	    }
	    
	    

	    public ImageAdapter(Context c) {
	    	getDrawables();
	    	
	        mContext = c;
	        TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
	        mGalleryItemBackground = a.getResourceId(
	                R.styleable.Gallery_android_galleryItemBackground, 0);
	        a.recycle();
	    }

	    public int getCount() {
	        return imagePaths.length;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    @Override	
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView i = new ImageView(mContext);
	        	      
	        i.setImageDrawable(downSampleImage(imagePaths[position]));
	        i.setLayoutParams(new android.widget.Gallery.LayoutParams(150, 100));
	        i.setScaleType(ImageView.ScaleType.FIT_XY);
	        i.setBackgroundResource(mGalleryItemBackground);

	        return i;
	    }
	    
	    private BitmapDrawable downSampleImage(String imgPath)
	    {
	    	BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inSampleSize = 2;
	    	Bitmap bmp = BitmapFactory.decodeFile(imgPath, options);	    
	    	
	    	BitmapDrawable rbmd = new BitmapDrawable(bmp);
	    	
	    	return rbmd;
	    }

	}
}
