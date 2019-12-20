package myUtil;

import essenses.Drink;
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
    public static List<Drink> readDrinksListFromXML(File file) throws ParserConfigurationException, IOException, SAXException {
        List<Drink> res = new ArrayList<>();

        DocumentBuilderFactory f = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList coffees = document.getElementsByTagName("coffee");
        for(int i = 0; i < coffees.getLength(); ++i){
            res.add(Parser.parseNodeToCoffee(coffees.item(i)));
        }

        NodeList teas = document.getElementsByTagName("tea");
        for(int i = 0; i < teas.getLength(); ++i){
            res.add(Parser.parseNodeToTea(teas.item(i)));
        }

        return res;
    }
}
