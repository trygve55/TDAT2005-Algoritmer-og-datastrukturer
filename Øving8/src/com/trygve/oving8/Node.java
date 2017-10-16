package com.trygve.oving8;

/**
 * Created by Trygve on 06.10.2017.
 */
public class Node {
    private int connection;
    private int length;
    private Node nextNode;

    public Node(int connection, int length) {
        this.connection = connection;
        this.length = length;
    }

    public int getConnection() {
        return connection;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public int getLength() {
        return length;
    }

    public void addConnection(int connection, int weight) {
        if (nextNode == null) nextNode = new Node(connection, weight);
        else nextNode.addConnection(connection, weight);
    }

}
