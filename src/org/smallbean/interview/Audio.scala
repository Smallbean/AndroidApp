package org.smallbean.interview


import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.AdapterView.OnItemClickListener


class Audio extends Activity {
          
    var audioFilePaths = Data.GetAudioURLs

    var isPlaying = false
    var playTask:PlayAudio = null
    var recordingFile:File = null
        
    override def onCreate(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.audio)
        
        var audio_gallery = findViewById(R.id.audio_gallery).asInstanceOf[android.widget.Gallery]
        
        audio_gallery.setAdapter(new AudioAdapter(this))

        audio_gallery.setOnItemClickListener(new OnItemClickListener() {
            def onItemClick(parent:AdapterView[_], view:View, position:Int, id:Long) { 
	        	recordingFile = new File(audioFilePaths(position))
	        	playTask = new PlayAudio()
	    		playTask.execute()
	        }
	    })
	    
    }
      
    
    def recordAudio(view:View) {
        val intent = new Intent(this, classOf[AudioRecorder])
        startActivityForResult(intent, 0)
    }
    
    class AudioAdapter(c:Context) extends BaseAdapter {
        var a = obtainStyledAttributes(R.styleable.Gallery)
        var mGalleryItemBackground = a.getResourceId(
                R.styleable.Gallery_android_galleryItemBackground, 0)
        a.recycle()
	    val audioFilePaths = Data.GetAudioURLs
	    var mContext:Context = c

	    def getCount:Int = audioFilePaths.length

	    def getItem(position:Int):Object = position.asInstanceOf[Object]

	    def getItemId(position:Int) = position

	    override def getView(position:Int, convertView:View, parent:ViewGroup):View = {
	    	
	    	var musicNote = new ImageView(mContext)
	    	var mBitmap = BitmapFactory.decodeResource(getResources(),
	    			   R.drawable.sub_audio_wotext)
	    	
	    	musicNote.setLayoutParams(new android.widget.Gallery.LayoutParams(250, 250))	    	
	    	musicNote.setBackgroundResource(mGalleryItemBackground)
	    	musicNote.setImageBitmap(mBitmap)
	    	
	        musicNote
	    }	   	   

	}
    
    class PlayAudio extends AsyncTask[AnyRef, Integer, AnyRef] {
    	
    	val frequency = 11025
    	val channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO
    	val audioEncoding = AudioFormat.ENCODING_PCM_16BIT
    	
		override protected def doInBackground(params:AnyRef*):AnyRef = {
			isPlaying = true

			var bufferSize:Int = AudioTrack.getMinBufferSize(frequency,	channelConfiguration, audioEncoding)
			
			var audiodata = new Array[Short](bufferSize / 4)
			
			try {
				var dis = new DataInputStream(
						new BufferedInputStream(new FileInputStream(
								recordingFile)))

				var audioTrack = new AudioTrack(
						AudioManager.STREAM_MUSIC, frequency,
						channelConfiguration, audioEncoding, bufferSize,
						AudioTrack.MODE_STREAM)

				audioTrack.play()

				while (isPlaying && dis.available() > 0) {
					var i = 0
					while (dis.available() > 0 && i < audiodata.length) {
						audiodata(i) = dis.readShort
						i = i + 1
					}
					audioTrack.write(audiodata, 0, audiodata.length)
				}

				dis.close()

			} catch {
				case _ => Log.e("AudioTrack", "Playback Failed")
			}
			return null
		}
	}
}
