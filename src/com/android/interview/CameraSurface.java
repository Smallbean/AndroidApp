package com.android.interview;


import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class CameraSurface extends Activity {	
	private ImageView img;
	private Intent cameraIntent;
	private int CAMERA_RESULT = 0;
	private int displayWidth;
	private int displayHeight;
	private String imageFilePath;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
    }
	
	
	public void takePhoto(View view)
	{
	       imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/image.jpg";
	       
	       showToast(this,imageFilePath);
	       	               
	        File imageFile = new File(this.imageFilePath);
	        Uri imageFileUri = Uri.fromFile(imageFile);
	        
	        Intent intent = new Intent(android.provider.MediaStore.INTENT_ACTION_VIDEO_CAMERA);
	        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
	        startActivityForResult(intent, this.CAMERA_RESULT);
		
	}

	public void recordVideo(View view)
	{
		
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
        	showImage();	
        }
        
    }
    
    private void showImage()
    {
    	
		Display currentDisplay = getWindowManager().getDefaultDisplay();
		this.displayWidth = currentDisplay.getWidth();
		this.displayHeight = currentDisplay.getHeight();
			
        this.img =  (ImageView)findViewById(R.id.image);  
        
        
    	BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
    	bmpFactoryOptions.inJustDecodeBounds = true;
    	
    
		Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
    	
    	int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/(float)displayHeight);
    	int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/(float)displayWidth);
    	
    	if(heightRatio > 1 && widthRatio > 1)
    	{
    		if(heightRatio > widthRatio)
    		{
    			bmpFactoryOptions.inSampleSize = heightRatio;
    		} else
    		{
    			bmpFactoryOptions.inSampleSize = widthRatio;
    		}
    	}
    	
    	bmpFactoryOptions.inJustDecodeBounds = false;
    	bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
    	
    	img.setImageBitmap(bmp);
    }
    
    private void showToast(Context mContext, String text) {
    	Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}
