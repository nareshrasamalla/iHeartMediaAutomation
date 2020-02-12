package com.framework;

import java.awt.AWTException; 
import java.io.File;
import java.io.IOException;


	
	public class PDFValidation {

	       public String[] getPDFData(String filePath) throws IOException, InterruptedException, AWTException {
	              Thread.sleep(4000);
	              
	              String filesName="report";
	        
	              System.out.println("File name :"+filesName);
	              PDFManager pdfManager = new PDFManager();

	              // Get the PDF file name
	              File dir = new File(filePath);

	              File[] files = dir.listFiles();
	              if (files == null || files.length == 0) {
	                     //     flag = false;
	              }
	              for (int i = 1; i < files.length; i++) {
	                     if(files[i].getName().contains(filesName)) {
	                           System.out.println("file name: "+files[i].getName());
	                           filesName=files[i].getName();
	                     }
	              }
	              pdfManager.setFilePath(filePath+"\\"+filesName+".pdf");

	              //pdfManager.setFilePath("C:\\Users\\Prl_bhanu.vutukuri"+"\\Downloads\\"+filesName);

	              String pdf =pdfManager.ToText();
	              String[] words = pdf.split(":");

	              System.out.println(words[0]);
	              System.out.println(words[1]);
	              System.out.println(words[2]);
	              System.out.println(words[3]);
	              System.out.println(words[4]);
	              System.out.println(words[5]);
	              System.out.println(words[6]);
	              System.out.println(words[7]);
	              System.out.println(words[8]);
	              System.out.println(words[9]);
	              System.out.println(words[10]);	           
	              return words;
	       }  
}
