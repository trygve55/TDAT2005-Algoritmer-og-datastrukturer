package com.trygve.oving4;

/**
 * Created by Trygve on 11.09.2017.
 */
public class NodeControl {
    private Node dummyNode;
    private int length;
    private Node element;

    public NodeControl() {
        dummyNode = new Node(-999.0);
        dummyNode.setBefore(dummyNode);
        dummyNode.setNext(dummyNode);
        element = dummyNode;
    }

    public void insertFront(double data) {
        length++;
        Node newNode = new Node(data);
        dummyNode.getNext().setBefore(newNode);
        newNode.setNext(dummyNode.getNext());
        newNode.setBefore(dummyNode);
        dummyNode.setNext(newNode);
    }

    public void insertBack(double data) {
        length++;
        Node newNode = new Node(data);
        dummyNode.getBefore().setNext(newNode);
        newNode.setBefore(dummyNode.getBefore());
        newNode.setNext(dummyNode);
        dummyNode.setBefore(newNode);
    }

    public void remove(Node element) {
        if (element == dummyNode) return;
        element.getNext().setBefore(element.getBefore());
        element.getBefore().setNext(element.getNext());
        length--;
    }

    public int getLength() {
        return length;
    }

    public double findBiggest() {
        double max = Double.MIN_VALUE;
        Node element = dummyNode.getNext();
        while (element != dummyNode) {
            if (max < element.getData()) max = element.getData();
            element = element.getNext();
        }

        return max;
    }

    public double findSmallest() {
        double min = Double.MAX_VALUE;
        Node element = dummyNode.getNext();
        while (element != dummyNode) {
            if (min > element.getData()) min = element.getData();
            element = element.getNext();
        }

        return min;
    }

    public double findAt(int index) {
        Node element = dummyNode.getNext();

        for (int i = 0; i < index;i++) {
            element = element.getNext();
        }
        return element.getData();
    }

    public Node getNodeFirst() {
        return dummyNode.getNext();
    }

    public Node getDummyNode() {
        return dummyNode;
    }
}

