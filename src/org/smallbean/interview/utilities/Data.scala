package org.smallbean.interview.utilities

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.UUID

import android.os.Environment

object Data {
	def self = this
	
	var subject = ""
	
   private def root: String = {
	   val appPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmallBean"
	   
	   val appDir = new File(appPath)
	   if(!appDir.exists || !appDir.isDirectory) {
		   createFolder(appPath)
	   }
	   
	   appPath
   }
	

   private def subjectPath: String = root + "/" + subject
      
   private def imageFolderPath: String =  subjectPath + "/images"
   
   private def videoFolderPath: String = subjectPath + "/videos"	   
   
   private def audioFolderPath: String = subjectPath + "/audio"	   
      
   private def noteFilePath: String = subjectPath + "/note.txt"	   
   
   def SetSubject(newSubject: String): Unit = {subject = newSubject}
      
   def GetSubjects:Array[String] = (new File(root)).list()
   
   def AddSubject(newSubject: String) {
	   // add the subject	   
	   subject = newSubject
	   
	   createFolder(subjectPath)
	   
	   SetNote("")
	   
	   createFolder(imageFolderPath)
	   createFolder(videoFolderPath)
	   createFolder(audioFolderPath)	   
   }
   
   def DeleteSubject: Unit = deleteFolder(subjectPath)
   
   def SetNote(note:String): Unit = {
	   val f = new File(noteFilePath)
	     
	   try {
		   if (!f.exists())
		   {
			   f.createNewFile()
		   }
		   
	        val gpxwriter = new FileWriter(f)
	        val out = new BufferedWriter(gpxwriter)
	        out.write(note)
	        out.close()
		    
		} catch {
			case e:Exception => ""
		}
   }
   
   def GetNote:String = {
	   var note = ""
	   val f = new File(noteFilePath)
	   
	   try {
		   val r = new BufferedReader(new FileReader(f))
		   val total = new StringBuilder()
		   var line = ""
		   line = r.readLine
		   total.append(line)
		   note = total.toString()
	   }
	     catch {
			case e:Exception => ""
		}
	   note
   }
      
   def GetNewPhotoURL = imageFolderPath + "/image_" + GetNewTimeAndUuid + ".jpg"
   
   def GetNewAudioURL = audioFolderPath + "/audio_" + GetNewTimeAndUuid + ".3gp"
   
   def GetNewVideoURL = videoFolderPath + "/video_" + GetNewTimeAndUuid + ".mp4"
   
   def GetPhotoURLs = GetListOfFilesInPath(imageFolderPath)
      
   def GetAudioURLs = GetListOfFilesInPath(audioFolderPath)
   
   def GetVideoURLs = GetListOfFilesInPath(videoFolderPath)
   
   def DeletePhoto(photoUrl:String) {
	   deleteFile(photoUrl)
   }

   def DeleteAudio(audioUrl:String) {
	   deleteFile(audioUrl)
   }
   
   def DeleteVideo(videoUrl:String) {
	   deleteFile(videoUrl)
   }
   
   private def createFolder(folderPath:String) {	   
	   (new File(folderPath)).mkdir
   }
   
   private def deleteFolder(folderPath:String) {
	   val file = new File(folderPath)
	   if(file.isDirectory && file.list().length > 0)
	   {
		   deleteDirectory(file)
	   }
	   else 
	   {
		   file.delete()
	   }	   	
   }
   
   private def deleteDirectory(path:File) {
	    if( path.exists() ) {
	      var files = path.listFiles()
	      // Delete directory and files
	    }
	    path.delete()
   }
   
   private def deleteFile(filePath:String) {
	   var fileToDelete = new File(filePath)
	   
	   fileToDelete.delete()
   }
   
   private def GetNewTimeAndUuid:String = {
	   val uuid = UUID.randomUUID()
	   val cal = Calendar.getInstance()
	
	   val date = cal.getTime()
	   val df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ssZ")
	   
	   df.format(date) + "_" + uuid.toString
   }
   

   private def GetListOfFilesInPath(path:String): Array[String] = (new File(path)).list.map(path + "/" + _)
}