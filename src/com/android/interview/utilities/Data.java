package com.android.interview.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

public class Data {
	
   private static Data instance = null;
   private static String subject = null;
   private static String subjectPath = null;   
   private static String imageFolderPath = null;
   private static String videoFolderPath = null;
   private static String audioFolderPath = null;
   
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
   
   public List<String> GetSubjects() {	   	   
	   List<String> subjects = new ArrayList<String>();
	   
	   File subjectDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
	   
	   for(String s : subjectDirectory.list()) {
		   subjects.add(s);
	   }
	   return subjects;
   }
   
   public void AddSubject(String subject) {
	   // add the subject	   
	   Data.subject = subject;	 
	   
	   Data.subjectPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Data.subject;
	   Data.imageFolderPath = Data.subjectPath + "/images";
	   Data.videoFolderPath = Data.subjectPath + "/videos";
	   Data.audioFolderPath = Data.audioFolderPath + "/audio_fiels";
	   
	   createFolder(Data.subjectPath);
	   createFolder(Data.imageFolderPath);
	   createFolder(Data.videoFolderPath);
	   createFolder(Data.audioFolderPath);
	   
   }
   
   public void SetNote(String note) {
	   
   }
   
   public String GetNote() {
	   return null;
   }
      
   public String GetPhotoURL() {
	   
	   return Environment.getExternalStorageDirectory().getAbsolutePath() + "/image.jpg";	   
   }
   
   public String GetAudioURL() {
	   return null;
   }
   
   public String GetVideoURL() {
	   return null;
   }

   
   public List<String> GetPhotoURLs() {
	   // get list of phto urls
	   return null;
   }
   
   public List<String> GetAudioURLs() {
	   return null;
   }
   
   public List<String> GetVideoURLs() {
	   return null;
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
	   
   }
   private void deleteFolder(String folderPath) {
	   
   }
   private void deleteFile(String filePath) {
	   
   }
  
      
   
   
   
}