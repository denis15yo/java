package myUtil;

import essenses.Coffee;
import essenses.CoffeeType;
import essenses.Tea;
import essenses.TeaType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.parsers.ParserConfigurationException;

public class Parser {
    public static Coffee parseNodeToCoffee(Node node) throws ParserConfigurationException, IllegalArgumentException {
        Coffee coffee = new Coffee();

        NamedNodeMap attributes = node.getAttributes();
        if(attributes.getLength() != 1){
            throw new ParserConfigurationException();
        }
        coffee.setCoffeeType(CoffeeType.valueOf(attributes.getNamedItem("coffeeType").getTextContent()));

        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "name":
                    coffee.setName(currentChild.getTextContent());
                    break;
                case "caffeine":
                    coffee.setCaffeine(Integer.parseInt(currentChild.getTextContent()));
                    break;
                case "cost":
                    coffee.setCost(Integer.parseInt(currentChild.getTextContent()));
                    break;
            }
            currentChild = currentChild.getNextSibling();
        }

        return coffee;
    }

    public static Tea parseNodeToTea(Node node) throws ParserConfigurationException, IllegalArgumentException {
        Tea tea = new Tea();

        NamedNodeMap attributes = node.getAttributes();
        if(attributes.getLength() != 1){
            throw new ParserConfigurationException();
        }
        tea.setTeaType(TeaType.valueOf(attributes.getNamedItem("teaType").getTextContent()));

        Node currentChild = node.getFirstChild(); // fix duplucate todo
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "name":
                    tea.setName(currentChild.getTextContent());
                    break;
                case "caffeine":
                    tea.setCaffeine(Integer.parseInt(currentChild.getTextContent()));
                    break;
                case "cost":
                    tea.setCost(Integer.parseInt(currentChild.getTextContent()));
                    break;
            }
            currentChild = currentChild.getNextSibling();
        }

        return tea;
    }
}
