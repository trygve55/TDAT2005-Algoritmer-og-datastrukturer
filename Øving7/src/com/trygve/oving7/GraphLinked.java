package com.trygve.oving7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Trygve on 07.10.2017.
 */
public class GraphLinked {
    Node[] nodes;

    public GraphLinked(int size) {
        nodes = new Node[size];
    }

    public void addNode(int index, int connection) {
        if (nodes[index] == null) nodes[index] = new Node(connection);
        else nodes[index].addConnection(connection);
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < nodes.length;i++) {
            if (nodes[i] != null) {
                string += "from: " + i + " to: ";
                Node node = nodes[i];
                string += node.getConnection() + ", ";
                while (node.getNextNode() != null) {
                    node = node.getNextNode();
                    string += node.getConnection() + ", ";
                }
                string += "\n";
            }
        }

        return string;
    }

    public int[] bfs(int fromNode, int toNode) {
        if (fromNode == toNode) return new int[]{};

        int[] cameFrom = new int[nodes.length];
        Arrays.fill(cameFrom, -1);

        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

        cameFrom[fromNode] = -2;
        queue.add(fromNode);

        while(!queue.isEmpty()) {
            int currentNode = (int) queue.poll();
            Node node = nodes[currentNode];
            while (node != null){
                if (node.getConnection() == toNode) {

                    int traceBackNode = currentNode;
                    List<Integer> passedNodes = new ArrayList();
                    passedNodes.add(0, toNode);

                    while (cameFrom[traceBackNode] != -2) {
                        passedNodes.add(0, traceBackNode);
                        traceBackNode = cameFrom[traceBackNode];
                    }

                    return passedNodes.stream().mapToInt(i->i).toArray();
                }

                if (cameFrom[node.getConnection()] == -1) {
                    cameFrom[node.getConnection()] = currentNode;
                    queue.add(node.getConnection());
                }

                node = node.getNextNode();
            }
        }

        return null;
    }

    public int[] topological() {

        boolean[] visited = new boolean[size()];
        List<Integer> passedNodes = new ArrayList();

        for (int i = 0; i < size();i++) {
            if (!visited[i]) {
                topologicalTryNode(i, visited, passedNodes);
            }
        }



        return passedNodes.stream().mapToInt(i->i).toArray();

        //return null;
    }

    private boolean topologicalTryNode(int nodeIndex, boolean[] visited, List<Integer> passedNodes) {
        visited[nodeIndex] = true;

        Node node = nodes[nodeIndex];

        while (node != null){

            if (!visited[node.getConnection()]) {
                topologicalTryNode(node.getConnection(), visited, passedNodes);
            }

            node = node.getNextNode();
        }

        passedNodes.add(0, nodeIndex);

        return false;
    }

    public int size() {
        return nodes.length;
    }
}
