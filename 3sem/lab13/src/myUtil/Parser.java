package myUtil;

import essenses.AgeBounds;
import essenses.Toy;
import org.w3c.dom.Node;

public class Parser {
    public static Toy parseToToy(String str) throws NumberFormatException{
        String[] strFields = str.split("\\s+");
        return new Toy(
                strFields[0],
                Integer.parseInt(strFields[1]),
                new AgeBounds(Integer.parseInt(strFields[2]), Integer.parseInt(strFields[3])));
    }

    public static Toy parseNodeToToy(Node node){
        Toy toy = new Toy();

        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            if(currentChild.getNodeType() == Node.ELEMENT_NODE) {
                String childName = currentChild.getNodeName();
                switch (childName) {
                    case "name":
                        toy.setName(currentChild.getTextContent());
                        break;
                    case "cost":
                        toy.setCost(Integer.parseInt(currentChild.getTextContent()));
                        break;
                    case "ageBounds":
                        toy.setAgeBounds(parseNodeToAgeBounds(currentChild));
                        break;
                }
            }
            currentChild = currentChild.getNextSibling();
        }

        return toy;
    }

    private static AgeBounds parseNodeToAgeBounds(Node node){
        AgeBounds ageBounds = new AgeBounds();

        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            if(currentChild.getNodeType() == Node.ELEMENT_NODE){
                String childName = currentChild.getNodeName();
                switch(childName) {
                    case "minAge":
                        ageBounds.setMin(Integer.parseInt(currentChild.getTextContent()));
                        break;
                    case "maxAge":
                        ageBounds.setMax(Integer.parseInt(currentChild.getTextContent()));
                        break;
                }
            }
            currentChild = currentChild.getNextSibling();
        }


        return ageBounds;
    }
}
