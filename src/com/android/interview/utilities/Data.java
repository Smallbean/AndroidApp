package com.android.interview.utilities;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import android.os.Environment;


public class Data {
	
   private static Data instance = null;
   private static String subject = null;
   

   private String root(){
	   String appPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmallBean";
	   
	   File appDir = new File(appPath);
	   if(!appDir.exists() || !appDir.isDirectory()) {
		   createFolder(appPath);
	   }
	   
	   return appPath;
   }
   
   private String subjectPath(){
	   return root() + "/" + Data.subject;
   }
      
   private String imageFolderPath(){
	   return subjectPath() + "/images";
   }
   
   private String videoFolderPath(){
	   return subjectPath() + "/videos";	   
   }
   
   private String audioFolderPath(){
	   return subjectPath() + "/audio_files";	   
   }
   
   protected Data() {
      // Exists only to defeat instantiation.
   }
   
   public static Data getInstance() {
      if(instance == null) {
         instance = new Data();
      }
      return instance;
   }
   
   public void SetSubject(String subject) {
	   Data.subject = subject;
   }
      
   public String[] GetSubjects() {	   	   	   	   	   
	   File files = new File(root());	 	 
	   return files.list();
   }
   
   public void AddSubject(String subject) {
	   // add the subject	   
	   Data.subject = subject;
	   
	   createFolder(subjectPath());
	   createFolder(imageFolderPath());
	   createFolder(videoFolderPath());
	   createFolder(audioFolderPath());	   
   }
   
   public void DeleteSubject() {	  
	   deleteFolder(subjectPath());
   }
   
   public void SetNote(String note) {
	   
   }
   
   public String GetNote() {
	   return "This is my note stub";
   }
      
   public String GetNewPhotoURL() {
	   return imageFolderPath() + "/image_" + GetNewTimeAndUuid() + ".jpg";
   }
   
   public String GetNewAudioURL() {
	   return audioFolderPath() + "/audio_" + GetNewTimeAndUuid() + ".3gp";
   }
   
   public String GetNewVideoURL() {
	   return videoFolderPath() + "/video_" + GetNewTimeAndUuid() + ".mp4";
   }
   
   public String[] GetPhotoURLs() {	   
	   return GetListOfFilesInPath(imageFolderPath());
   }
      
   public String[] GetAudioURLs() {
	   return GetListOfFilesInPath(imageFolderPath());
   }
   
   public String[] GetVideoURLs() {
	   return GetListOfFilesInPath(imageFolderPath());
   }
   
   public void DeletePhoto(String photoUrl) {
	   deleteFile(photoUrl);
   }

   public void DeleteAudio(String audioUrl) {
	   deleteFile(audioUrl);
   }
   
   public void DeleteVideo(String videoUrl) {
	   deleteFile(videoUrl);
   }
   
   private void createFolder(String folderPath) {	   
	   (new File(folderPath)).mkdir();
   }
   
   private void deleteFolder(String folderPath) {
	   File file = new File(folderPath);
	   if(file.isDirectory() && file.list().length>0)
	   {
		   deleteDirectory(file);
	   }
	   else 
	   {
		   file.delete();
	   }
	   	
   }
   
   private boolean deleteDirectory(File path) {
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           deleteDirectory(files[i]);
	         }
	         else {
	           files[i].delete();
	         }
	      }
	    }
	    return( path.delete() );
   }
   
   private void deleteFile(String filePath) {
	   File fileToDelete = new File(filePath);
	   
	   fileToDelete.delete();
   }
   
   private String GetNewTimeAndUuid(){
	   UUID uuid = UUID.randomUUID();
	   Calendar cal = Calendar.getInstance();
	   Long time = cal.getTimeInMillis();
	   return time.toString() + "_" + uuid.toString();
   }
   

   private String[] GetListOfFilesInPath(String path){
	   File folder = new File(path);
	   
	   String[] files = folder.list();
	   
	   for(int i=0;i<files.length;i++)
	   {
		   files[i] = path + "/" + files[i];
	   }
	   
	   return files;	   
   }
   
  
 
}