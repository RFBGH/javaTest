package com.rfb.demo.rxjavatest.toolkit;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/22 0022.
 */
public class StringXmlUtils {

    private static Set<String> getStringXmlNames(String path){

        Set<String>nameSet = new HashSet<String>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(path);
            NodeList nameList = document.getElementsByTagName("string");
            for (int i = 0; i < nameList.getLength(); i++) {
                Node node = nameList.item(i);
                NamedNodeMap attrs = node.getAttributes();
                String name = attrs.getNamedItem("name").getNodeValue();
                nameSet.add(name);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nameSet;
    }

    public static void divideUntranslateString(String path1, String path2) {

        Set<String> nameSet = getStringXmlNames(path1);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(path2);
            NodeList nameList = document.getElementsByTagName("string");
            for (int i = 0; i < nameList.getLength(); i++) {
                Node node = nameList.item(i);
                NamedNodeMap attrs = node.getAttributes();
                String name = attrs.getNamedItem("name").getNodeValue();

                if (nameSet.contains(name)) {
                    continue;
                }

                if(node.getFirstChild() != null){
                    System.out.println("<string name=\""+name+"\">"+node.getFirstChild().getNodeValue()+"</string>");
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
