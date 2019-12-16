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
    public static List<Worker> readListOfWorkersFromXML(File file) throws ParserConfigurationException, IOException, SAXException {
        List<Worker> res = new ArrayList<>();

        DocumentBuilderFactory f = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        Document document = builder.parse(file);
        NodeList workerNodes = document.getElementsByTagName("worker");

        for(int i = 0; i < workerNodes.getLength(); ++i){
            res.add(Parser.parseNodeToWorker(workerNodes.item(i)));
        }

        return res;
    }
}
