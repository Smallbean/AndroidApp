package org.smallbean.interview;

import org.smallbean.interview.utilities.Data;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            //TODO: Show the selected image in Full Screen
	        	Toast.makeText(Gallery.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	}
		
	public class ImageAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	    private String[] imagePaths;
	    private Context mContext;

	    	    	   
	    public ImageAdapter(Context c) {	    		    		    		    		    
	        mContext = c;
	        imagePaths = data.GetPhotoURLs();	        	
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
	    
	    private BitmapDrawable downSampleImage(String imgPath) {
	    	BitmapFactory.Options options = new BitmapFactory.Options();	    	
	        options.inSampleSize = 2;
	        
	    	Bitmap bmp = BitmapFactory.decodeFile(imgPath, options);	    		   
	    	
	    	BitmapDrawable rbmd = new BitmapDrawable(bmp);	    	
	    	
	    	return rbmd;
	    }
	}
}
