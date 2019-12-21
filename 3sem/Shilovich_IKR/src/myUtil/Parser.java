package myUtil;

import essenses.*;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.parsers.ParserConfigurationException;

public class Parser {
    public static SecurityGuard parseNodeToSecurityGuard(Node node) throws ParserConfigurationException, IllegalArgumentException {
        SecurityGuard securityGuard = new SecurityGuard();

        NamedNodeMap attributes = node.getAttributes();
        if(attributes.getLength() != 1){
            throw new ParserConfigurationException();
        }
        securityGuard.setGuardedArea(Integer.parseInt(attributes.getNamedItem("guardedArea").getTextContent()));

        getWorkerInfo(node, securityGuard);

        return securityGuard;
    }

    public static Seller parseNodeToSeller(Node node) throws ParserConfigurationException, IllegalArgumentException {
        Seller tea = new Seller();

        NamedNodeMap attributes = node.getAttributes();
        if(attributes.getLength() != 1){
            throw new ParserConfigurationException();
        }
        tea.setRevenue(Integer.parseInt(attributes.getNamedItem("revenue").getTextContent()));

        getWorkerInfo(node, tea);

        return tea;
    }

    private static void getWorkerInfo(Node node, Worker worker){
        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "surname":
                    worker.setSurname(currentChild.getTextContent());
                    break;
                case "organization":
                    worker.setOrganization(currentChild.getTextContent());
                    break;
                case "jobRatio":
                    worker.setJobRatio(Integer.parseInt(currentChild.getTextContent()));
                    break;
            }
            currentChild = currentChild.getNextSibling();
        }
    }
}
