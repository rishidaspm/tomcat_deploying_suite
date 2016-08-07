package com.st.xml;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XmlManager {
	
	private Document document;
	
	
	
	public void readXml(File file)throws Exception{
		
		try {
	         
	         SAXReader reader = new SAXReader();
	          document = reader.read( file );

	         System.out.println("Root element :" 
	            + document.getRootElement().getName());

	         Element classElement = document.getRootElement();

	       
	         
	      } catch (DocumentException e) {
	         throw e;
	         }
	}
	
	public String  getSingleNodeAttributeValue(String nodeName,String attributeName)throws Exception {
		
		
		  Node node = document.selectSingleNode(nodeName);
		  String attributeValue = node.valueOf(attributeName);
		  
		  return attributeValue;
		  
	    
		
	}
	
	public Node getNodeWithAttributeValue(String nodeName,String AttributeName,String attributeValue)throws Exception{
		
		
		List<Node> nodes = document.selectNodes(nodeName);
        System.out.println("----------------------------");
        for (Node node : nodes) {
           System.out.println("\nCurrent Element :" 
              + node.getName());
           
           String avalue= node.valueOf(AttributeName).trim();
           if(avalue.equalsIgnoreCase(attributeValue)){
        	   return node;
           }
           
        
        }
        
        return null;
	}

}
