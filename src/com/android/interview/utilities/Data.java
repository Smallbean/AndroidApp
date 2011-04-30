package com.android.interview.utilities;

import java.util.ArrayList;
import java.util.List;

public class Data {
	   private static Data instance = null;
	   protected Data() {
	      // Exists only to defeat instantiation.
	   }
	   public static Data getInstance() {
	      if(instance == null) {
	         instance = new Data();
	      }
	      return instance;
	   }
	   
	   public List<String> GetSubjects(){
		   List<String> test = new ArrayList<String>();
		   
		   test.add("Noah");
		   test.add("Sean");
		   
		   return test;
	   }
	   
	   public void AddSubject(String subject){
		   // add the subject
	   }
	   
	}