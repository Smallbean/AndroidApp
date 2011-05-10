package org.smallbean.interview

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

import org.smallbean.interview.utilities.Data

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaRecorder
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class AudioRecorder extends Activity with OnClickListener {

    val CAMERA_RESULT = 1
	
	val recordTask:RecordAudio = null
	val playTask:PlayAudio = null
	
	val startRecordingButton = findViewById(R.id.StartRecordingButton).asInstanceOf[Button]
	val startPlaybackButton = findViewById(R.id.StartPlaybackButton).asInstanceOf[Button]
	val stopPlaybackButton = findViewById(R.id.StopPlaybackButton).asInstanceOf[Button]
	
	val durationText:TextView = findViewById(R.id.DurationTextView).asInstanceOf[TextView]
	
	var recordingFile:File = null

	var isRecording = false
	var isPlaying = false

	var frequency = 11025
	var channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO
	var audioEncoding = AudioFormat.ENCODING_PCM_16BIT

	var startRecordTime:Long = null

	override def onCreate(savedInstanceState:Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.audio_recorder)

		startRecordingButton.setOnClickListener(this)
		startPlaybackButton.setOnClickListener(this)
		stopPlaybackButton.setOnClickListener(this)

		startPlaybackButton.setEnabled(false)
		stopPlaybackButton.setEnabled(false)

		val audioFilename = Data.GetNewAudioURL()
		recordingFile = new File(audioFilename)

		showToast(this,audioFilename)       	               
        
		startRecordTime = System.currentTimeMillis()
		// Start recording
		record()
	}

	def onClick(v:View) {
		if (v == startRecordingButton) {
			record
		} else if (v == startPlaybackButton) {
			play
		} else if (v == stopPlaybackButton) {
			stopPlaying
		}
	}

	def play {
		startPlaybackButton.setEnabled(true)

		playTask = new PlayAudio()
		playTask.execute()

		stopPlaybackButton.setEnabled(true)
	}

	def stopPlaying() {
		isPlaying = false
		stopPlaybackButton.setEnabled(false)
		startPlaybackButton.setEnabled(true)
	}

	def record() {
		startRecordingButton.setEnabled(false)

		recordTask = new RecordAudio()
		recordTask.execute()
	}

	def stopRecording() {
		isRecording = false
	}
	
	def stopRecording(view:View) {
		stopRecording()
	}
	
	def takePhoto(view:View) {		
		takePhoto()
	}	
	def takePhoto{
        
		val photoFilename = Data.GetNewPhotoURL()
		showToast(this,photoFilename)       	               
        
        val imageFileUri = Uri.fromFile(new File(photoFilename))
        
        val intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri)
        
        startActivityForResult(intent, CAMERA_RESULT)               		
	}

	def showToast(mContext:Context, text:String) {
    	Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
    }
	
    def onActivityResult(requestCode:Int, resultCode:Int, image:Intent) {
        super.onActivityResult(requestCode, resultCode, image)
    }

	class PlayAudio extends AsyncTask[Unit, Integer, Unit] {
		override protected def doInBackground {
			isPlaying = true

			var bufferSize = AudioTrack.getMinBufferSize(frequency,
					channelConfiguration, audioEncoding)
			val size:Int = bufferSize / 4
			var audiodata = new short[size]

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
						audiodata[i] = dis.readShort()
						i++
					}
					audioTrack.write(audiodata, 0, audiodata.length)
				}

				dis.close()

				startPlaybackButton.setEnabled(false)
				stopPlaybackButton.setEnabled(true)

			} catch {
				case _ => Log.e("AudioRecord", "Playback Failed")
			}

			return null
		}
	}

	class RecordAudio extends AsyncTask[Unit, Integer, Void] {
		override protected def doInBackground(params:Unit*) {
			isRecording = true

			try {
				var dos = new DataOutputStream(
						new BufferedOutputStream(new FileOutputStream(
								recordingFile)))

				var bufferSize = AudioRecord.getMinBufferSize(frequency,
						channelConfiguration, audioEncoding)

				var audioRecord = new AudioRecord(
						MediaRecorder.AudioSource.MIC, frequency,
						channelConfiguration, audioEncoding, bufferSize)

				var buffer = new short[bufferSize]
				audioRecord.startRecording()

				var r = 0
				while (isRecording) {
					var bufferReadResult = audioRecord.read(buffer, 0,
							bufferSize)
					for (i <- 0 until bufferReadResult)
						dos.writeShort(buffer[i])
					}

					publishProgress(new Integer(r))
					r++
				}

				audioRecord.stop()
				dos.close()
			} catch {
				case _ => Log.e("AudioRecord", "Recording Failed")
			}

			return null
		}

		def onProgressUpdate(progress:Int*) {
			var duration = System.currentTimeMillis() - startRecordTime
			var df = new SimpleDateFormat("mm:ss")			
			durationText.setText("Time:" + df.format(new Date(duration)))
		}

		def onPostExecute(result:Unit) {
			startRecordingButton.setEnabled(true)
			startPlaybackButton.setEnabled(true)
		}
	}
	
}
