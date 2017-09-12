package com.trygve.oving4;

public class Main {

    public static void main(String[] args) {
        int persons = 40;
        int killEvery = 3;

        NodeControl nodes = new NodeControl();

        for (int i = 0; i < persons;i++) nodes.insertBack(i + 1);

        Node element = nodes.getNodeFirst();

        while (nodes.getLength() > 1) {
            for (int i = 0; i < killEvery - 1; i++) {
                element = element.getNext();
                if (element == nodes.getDummyNode()) element = element.getNext();
            }
            Node ele = element.getNext();
            //System.out.println("eliminiated: " + element.getData());
            nodes.remove(element);
            element = ele;
            if (element == nodes.getDummyNode()) element = element.getNext();

            //System.out.println(getAll(nodes));
        }

        System.out.println("The survivor is person number " + element.getData());
    }

    private static String getAll(NodeControl nodes) {
        String string = "";
        for (int i = 0; i < nodes.getLength();i++) {
            string += nodes.findAt(i) + ", ";
        }
        return string;
    }
}
