package myUtil;

import essenses.AgeBounds;
import essenses.Toy;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    public static List<Toy> readListOfToys(File file) throws FileNotFoundException, NumberFormatException {
        List<Toy> l = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            l.add(Parser.parseToToy(sc.nextLine()));
        }
        return l;
    }

    public static List<Toy> readListOfToysFromXML(File file) throws ParserConfigurationException, IOException, SAXException {
        List<Toy> res = new ArrayList<>();

        DocumentBuilderFactory f = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        Document document = builder.parse(file);
        NodeList toys = document.getDocumentElement().getElementsByTagName("toy");

        for(int i = 0; i < toys.getLength(); ++i){
            res.add(Parser.parseNodeToToy(toys.item(i)));
        }

        return res;
    }
}
