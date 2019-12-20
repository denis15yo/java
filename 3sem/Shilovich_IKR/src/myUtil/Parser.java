package myUtil;

import essenses.*;
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

        getDrinkInfo(node, coffee);

        return coffee;
    }

    public static Tea parseNodeToTea(Node node) throws ParserConfigurationException, IllegalArgumentException {
        Tea tea = new Tea();

        NamedNodeMap attributes = node.getAttributes();
        if(attributes.getLength() != 1){
            throw new ParserConfigurationException();
        }
        tea.setTeaType(TeaType.valueOf(attributes.getNamedItem("teaType").getTextContent()));

        getDrinkInfo(node, tea);

        return tea;
    }

    private static void getDrinkInfo(Node node, Drink drink){
        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "name":
                    drink.setName(currentChild.getTextContent());
                    break;
                case "caffeine":
                    drink.setCaffeine(Integer.parseInt(currentChild.getTextContent()));
                    break;
                case "cost":
                    drink.setCost(Integer.parseInt(currentChild.getTextContent()));
                    break;
            }
            currentChild = currentChild.getNextSibling();
        }
    }
}
