package com.android.interview;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;


public class Gallery extends Activity {

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
	    private Context mContext;

	    private Integer[] mImageIds = {
	          0
	    };

	    public ImageAdapter(Context c) {
	        mContext = c;
	        TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
	        mGalleryItemBackground = a.getResourceId(
	                R.styleable.Gallery_android_galleryItemBackground, 0);
	        a.recycle();
	    }

	    public int getCount() {
	        return mImageIds.length;
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
	        
	        i.setImageResource(mImageIds[position]);
	        i.setLayoutParams(new android.widget.Gallery.LayoutParams(150, 100));
	        i.setScaleType(ImageView.ScaleType.FIT_XY);
	        i.setBackgroundResource(mGalleryItemBackground);

	        return i;
	    }

	}
}
