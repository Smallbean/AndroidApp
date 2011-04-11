package com.android.interview;


import java.io.File;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CameraSurface extends Activity {	
	private ImageView img;
	private Intent cameraIntent;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
                
        this.img =  (ImageView)findViewById(R.id.image);
        
        cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                       
        //cameraIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(cameraIntent,0);
        
    }
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);                       
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        img.setImageBitmap(thumbnail);       
    }
    
    private void showToast(Context mContext, String text) {
    	Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}
