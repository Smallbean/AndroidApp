package com.android.interview;


import java.io.File;

import com.android.interview.utilities.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class CameraSurface extends Activity {	
	private int CAMERA_RESULT = 1;
	private Data data = Data.getInstance();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
                         
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
        	                
            Intent intent = new Intent(this, Gallery.class);
            startActivityForResult(intent, 0);                    	
        }
               
    }
    
    private void showToast(Context mContext, String text) {
    	Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}
