package myUtil;

import essenses.Worker;
import org.w3c.dom.Node;

public class Parser {
    public static Worker parseNodeToWorker(Node node){
        Worker worker = new Worker();

        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "surname":
                    worker.setSurname(currentChild.getTextContent());
                    break;
                case "post":
                    worker.setPost(currentChild.getTextContent());
                    break;
                case "salary":
                    worker.setSalary(Integer.parseInt(currentChild.getTextContent()));
            }
            currentChild = currentChild.getNextSibling();
        }

        return worker;
    }
}
