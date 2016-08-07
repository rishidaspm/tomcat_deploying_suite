package com.st.main;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import org.dom4j.Node;

import com.st.browser.BrowserManager;
import com.st.fileservice.FileManager;
import com.st.xml.XmlManager;

public class StartTomcat {

	public static void main(String[] args) {
		
		
		
		
		try {
			//create bat file first
			File batFile = new File("starttom.bat");
			String appPath = new File(".").getCanonicalPath();
			String[] split = appPath.split(":");
			String drive = split[0]+":";
			
			String tomcateBinDir = appPath+"\\"+"apache-tomcat-server\\bin";
			
			//create file content
			TreeMap<Integer, String> fileContent = new TreeMap<>();
			
			fileContent.put(fileContent.size(), "cd\\");
			fileContent.put(fileContent.size(), "cd "+tomcateBinDir);
			fileContent.put(fileContent.size(), drive);
			fileContent.put(fileContent.size(), "startup.bat");
			
			FileManager fm = new FileManager();
			fm.writeFile(batFile, fileContent);
			
			
			ProcessBuilder pb = new ProcessBuilder(batFile.getPath());
			pb.start();
			//pb.wait();
			
			Thread.sleep(15000);
			
			
			//NOW READ SERVER XML
			File serverXml = new File(appPath+"\\"+"apache-tomcat-server\\conf\\server.xml");
			XmlManager xm = new XmlManager();
			
			xm.readXml(serverXml);
			
			Node node = xm.getNodeWithAttributeValue("/Server/Service/Connector", "@protocol","HTTP/1.1");
			
			if(node!=null){
				String httpPort = node.valueOf("@port");
				
				String browserUrl = "http://localhost:"+httpPort;
				
				//now call browser manager
				BrowserManager bm = new BrowserManager();
				bm.setUrl(browserUrl);
				
				bm.startBrowser();
			}
					
			
			
			//now delete bat files
			System.gc();
			batFile.delete();
					
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.gc();
			System.exit(0);
		}
		
		

	}

}
