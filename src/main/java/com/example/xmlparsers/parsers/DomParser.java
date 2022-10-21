package com.example.xmlparsers.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomParser {

    public void parseXmlDocument(){
        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            File file = new File("src/main/java/com/example/xmlparsers/exampleData/employees.xml");
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            Node first = doc.getElementsByTagName("Employee").item(0);
            NodeList nodeList = first.getChildNodes();
            int n = nodeList.getLength();
            Node current;
            for (int i=0; i<n; i++) {
                current = nodeList.item(i);
                if(current.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(
                            current.getNodeName() + ": " + current.getTextContent());
                }
            }



        }catch (Exception e){throw new IllegalArgumentException(e);}
    }

    private void DomParser(){}
}
