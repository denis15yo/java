package my_util;

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
        NodeList toys = document.getDocumentElement().getElementsByTagName("Toy");

        for(int i = 0; i < toys.getLength(); ++i){
            NodeList fields = toys.item(i).getChildNodes();

            String name = "";
            int cost = 0;
            AgeBounds ageBounds = new AgeBounds();

            for(int j = 1; j < fields.getLength(); j += 2){
                Node field = fields.item(j);
                String fieldName = field.getNodeName();

                switch(fieldName) {
                    case "name":
                        name = field.getTextContent();
                        break;
                    case "cost":
                        cost = Integer.parseInt(field.getTextContent());
                        break;
                    case "ageBounds":
                        ageBounds = parseNodeAgeBounds(field);
                        break;
                }
            }
            res.add(new Toy(name, cost, ageBounds));
        }

        return res;
    }

    private static AgeBounds parseNodeAgeBounds(Node node){
        int minAge = 0;
        int maxAge = 0;

        NodeList fields = node.getChildNodes();
        for(int i = 1; i < fields.getLength(); i += 2){
            Node field = fields.item(i);
            String fieldName = field.getNodeName();

            switch(fieldName) {
                case "minAge":
                    minAge = Integer.parseInt(field.getTextContent());
                    break;
                case "maxAge":
                    maxAge = Integer.parseInt(field.getTextContent());
                    break;
            }
        }

        return new AgeBounds(minAge, maxAge);
    }
}
