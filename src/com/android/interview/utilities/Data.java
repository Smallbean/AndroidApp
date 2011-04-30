package com.android.interview.utilities;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
   private static Data instance = null;
   private static String subject = null;
   
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
	   this.subject = subject;
   }
   
   public List<String> GetSubjects() {
	   List<String> test = new ArrayList<String>();
	   
	   test.add("Noah");
	   test.add("Sean");
	   
	   return test;
   }
   
   public void AddSubject(String subject) {
	   // add the subject
   }
   
   public void SetNote(String note) {
	   
   }
   
   public String GetNote() {
	   return null;
   }
      
   public String GetPhotoURL() {
	   // get photo url
	   return null;	   
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
   
   public void DeletePhoto(String url) {
	   //delete a photo
   }

   public void DeleteAudio(String url) {
	   //delete a audio file
   }
   
   public void DeleteVideo(String url) {
	   //delete a video 
   }
   
  
      
   
   
   
}