package com.android.interview;

import com.android.interview.utilities.Data;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;


public class Gallery extends Activity {
	

	private Data data = Data.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.image_gallery);
	    
	    android.widget.Gallery g = (android.widget.Gallery)findViewById(R.id.gallery_layout);
	    	   
	    
	    g.setAdapter(new ImageAdapter(this));

	    g.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView parent, View v, int position, long id) {
	            Toast.makeText(Gallery.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	}
	
	
	public class ImageAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	   // private Drawable img;
	   // private Drawable[] imgs;
	    private String[] imagePaths;
	    private Context mContext;

	    private void getDrawables()
	    {
	    	String[] images = data.GetPhotoURLs();
	    	//imgs = new Drawable[images.length];
	    	imagePaths = new String[images.length];
	    	
	    	for(int i=0;i<images.length;i++)
	    	{
		    	DrawableManager dm = new DrawableManager();	    			    	
		    	//img = dm.fetchDrawable(images[i]);
		    	//imgs[i] = dm.fetchDrawable(images[i]);
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
	        
	        //i.setImageResource(mImageIds[position]);
	        //downSampleImage(imagePaths[position]);
	        //i.setImageDrawable(imgs[position]);
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
	    	
	    	//BitmapDrawable bmd = new BitmapDrawable(imgPath);
	    	//Bitmap bmp = bmd.getBitmap();
	    	
	 /*   	int width = bmp.getWidth();
	    	int height = bmp.getHeight();
	    	int newWidth = 50;
	    	int newHeight = 50;
	    	
	    	float scaleWidth = ((float)newWidth) / width;
	    	float scaleHeight = ((float)newHeight) / height;
	    	
	    	Matrix matrix = new Matrix();
	    	matrix.postScale(scaleWidth, scaleHeight);
	    	matrix.postRotate(45);
	    	
	    	Bitmap resizedBmp = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);
	    	
	    	*/
	    	
	    	BitmapDrawable rbmd = new BitmapDrawable(bmp);
	    	
	    	return rbmd;
	    }

	}
}
