package myUtil;

import essenses.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {


    public static List<Worker> readWorkersListFromXML(File file) throws ParserConfigurationException, SAXException,
            IllegalArgumentException, IOException {
        List<Worker> res = new ArrayList<>();

        DocumentBuilderFactory f = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList securityGuards = document.getElementsByTagName("securityGuard");
        for(int i = 0; i < securityGuards.getLength(); ++i){
            res.add(Parser.parseNodeToSecurityGuard(securityGuards.item(i)));
        }

        NodeList sellers = document.getElementsByTagName("seller");
        for(int i = 0; i < sellers.getLength(); ++i){
            res.add(Parser.parseNodeToSeller(sellers.item(i)));
        }

        return res;
    }
}
