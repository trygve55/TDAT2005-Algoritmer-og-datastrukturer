package com.trygve.oving8;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Trygve on 07.10.2017.
 */
public class GraphLinked {
    private Node[] nodes;
    final boolean undirected;

    public GraphLinked(int size, boolean undirected) {
        nodes = new Node[size];
        this.undirected = undirected;
    }

    public void addNode(int index, int connection, int weight) {
        if (nodes[index] == null) nodes[index] = new Node(connection, weight);
        else nodes[index].addConnection(connection, weight);

        if (undirected) {
            if (nodes[connection] == null) nodes[connection] = new Node(index, weight);
            else nodes[connection].addConnection(index, weight);
        }
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

    public int[][] dijkstra(int fromNode) {

        int[] lengthFromStart = new int[nodes.length];
        int[] cameFrom = new int[nodes.length];

        LengthFromSourceComparator comparator = new LengthFromSourceComparator(lengthFromStart);
        PriorityQueue<Integer> queue = new PriorityQueue<>(size(), comparator);

        for (int i = 0;i < size();i++) {
            lengthFromStart[i] = 10000000;
            cameFrom[i] = 10000000;
        }

        lengthFromStart[fromNode] = 0;
        cameFrom[fromNode] = -2;

        queue.add(fromNode);

        int nodeIndex, totalLength, totalNodes = 0;
        Node node;

        while (!queue.isEmpty()) {

            nodeIndex = queue.poll();
            node = nodes[nodeIndex];
            totalNodes++;

            while (node != null) {

                totalLength = lengthFromStart[nodeIndex] + node.getLength();

                if (totalLength < lengthFromStart[node.getConnection()]) {
                    lengthFromStart[node.getConnection()] = totalLength;
                    cameFrom[node.getConnection()] = nodeIndex;

                    queue.remove(node.getConnection());
                    queue.add(node.getConnection());
                }

                node = node.getNextNode();
            }
        }

        return new int[][]{lengthFromStart, cameFrom};
    }

    public int[][] dijkstra(int fromNode, int toNode) {

        int[] lengthFromStart = new int[nodes.length];
        int[] cameFrom = new int[nodes.length];

        LengthFromSourceComparator comparator = new LengthFromSourceComparator(lengthFromStart);
        PriorityQueue<Integer> queue = new PriorityQueue<>(size(), comparator);

        for (int i = 0;i < size();i++) {
            lengthFromStart[i] = 10000000;
            cameFrom[i] = 10000000;
        }

        lengthFromStart[fromNode] = 0;
        cameFrom[fromNode] = -2;

        queue.add(fromNode);

        int nodeIndex, totalLength, totalNodes = 0;
        Node node;

        while (!queue.isEmpty()) {

            nodeIndex = queue.poll();

            if (nodeIndex == toNode) break;

            node = nodes[nodeIndex];
            totalNodes++;

            while (node != null) {

                totalLength = lengthFromStart[nodeIndex] + node.getLength();

                if (totalLength < lengthFromStart[node.getConnection()]) {
                    lengthFromStart[node.getConnection()] = totalLength;
                    cameFrom[node.getConnection()] = nodeIndex;

                    if (toNode == node.getConnection()) queue.remove(node.getConnection());
                    queue.add(node.getConnection());
                }

                node = node.getNextNode();
            }
        }

        int traceBackNode = toNode;
        List<Integer> passedNodes = new ArrayList();
        passedNodes.add(0, toNode);

        while (cameFrom[traceBackNode] != -2) {
            passedNodes.add(0, traceBackNode);
            traceBackNode = cameFrom[traceBackNode];
        }

        return new int[][]{passedNodes.stream().mapToInt(i->i).toArray(), new int[]{lengthFromStart[toNode]}};
    }

    public int[][] prims(int fromNode) {
        if (!undirected) throw new IllegalArgumentException("Prims can not be run an undirected graph.");

        int[] length = new int[nodes.length];

        int[] cameFrom = new int[nodes.length];
        Arrays.fill(cameFrom, -1);
        cameFrom[fromNode] = -2;

        ArrayList<Integer> tree = new ArrayList<>();
        tree.add(fromNode);

        int shortestEdge, shortestEdgeLength, shortestEdgeFrom;
        Node node;

        while (true) {
            shortestEdge = -1;
            shortestEdgeLength = Integer.MAX_VALUE;
            shortestEdgeFrom = -1;

            for (Integer nodeIndex: tree) {
                node = nodes[nodeIndex];

                while (node != null) {

                    if (cameFrom[node.getConnection()] == -1 && node.getLength() < shortestEdgeLength) {
                        shortestEdge = node.getConnection();
                        shortestEdgeLength = node.getLength();
                        shortestEdgeFrom = nodeIndex;

                    }

                    node = node.getNextNode();
                }
            }

            if (shortestEdge == -1) {
                break;
            } else {
                tree.add(shortestEdge);
                cameFrom[shortestEdge] = shortestEdgeFrom;
                length[shortestEdge] = shortestEdgeLength;
            }
        }

        return new int[][]{cameFrom, length};
    }

    public int[] bfs(int fromNode, int toNode) {
        if (fromNode == toNode) return new int[]{};

        int[] cameFrom = new int[nodes.length];
        Arrays.fill(cameFrom, -1);

        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

        cameFrom[fromNode] = -2;
        queue.add(fromNode);

        int currentNode;
        Node node;

        while(!queue.isEmpty()) {
            currentNode = (int) queue.poll();
            node = nodes[currentNode];
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

    private class LengthFromSourceComparator implements Comparator<Integer>
    {
        private int[] lengthFromSource;

        public LengthFromSourceComparator(int[] lengthFromSource) {
            this.lengthFromSource = lengthFromSource;
        }

        @Override
        public int compare(Integer node1, Integer node2) {
            if (lengthFromSource[node1] < lengthFromSource[node2]) return -1;
            if (lengthFromSource[node1] > lengthFromSource[node2]) return 1;
            return 0;
        }
    }
}

