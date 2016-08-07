package com.st.fileservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.TreeMap;

public class FileManager {
	
	
	public void writeFile(File file,TreeMap<Integer, String> fileContent)throws Exception{
		
		BufferedWriter bw = null;
		FileWriter fw= null;
				
				try{
					bw = new BufferedWriter(new FileWriter(file));
					
					Iterator it = fileContent.keySet().iterator();
					
					while(it.hasNext()) {
						
						String line = fileContent.get(it.next());
						bw.write(line);
						bw.newLine();
					}
				}
		         catch(Exception e){
		        	 throw e;
		         }
				finally{
					
					if(bw !=null)
						bw.close();
					
					if(fw!=null)
						fw.close();
					
				}
		        
	}

}
