package com.trygve.oving8;

/**
 * Created by Trygve on 07.10.2017.
 */
public class NodePath {
    GraphLinked graph;

    String startLog = "";

    public NodePath(String filenameGraph) {
        this(filenameGraph, false);
    }

    public NodePath(String filenameGraph, boolean undirected) {
        long startTime = System.currentTimeMillis();
        graph = GraphFileReader.readGraphFile(filenameGraph, undirected);
        startLog += "Loaded graph file '" + filenameGraph + "' in " + (System.currentTimeMillis()-startTime) + "ms.\n";
    }

    public String navigate(int from, int to) {
        String string = "";
        long startTime = System.currentTimeMillis();
        int[] nodes = graph.bfs(from, to);
        if (nodes == null) string += "There are no routes between " + from + " and " + to + ".\n";
        else string += "There are " + nodes.length + " nodes between " + from + " and " + to + ".\n";
        return string;
    }

    public String showAll() {
        String string = "";
        for (int fromNode = 0; fromNode < graph.size();fromNode++) {
            string += "\n" + fromNode + "\nNode Forgj Dist\n";
            for (int toNode = 0; toNode < graph.size();toNode++) {
                string += "   " + toNode;
                int[] data = graph.bfs(fromNode, toNode);
                if (data == null) {
                    string += "     -    -";
                } else {
                    string += "     ";
                    if (data.length == 0) string += " ";
                    else string += data[0];
                    string += "    " + data.length;
                }
                string += "\n";
            }
        }
        return string;
    }

    public String showTopological() {
        String string = "Topological: ";

        int[] topoNodes = graph.topological();
        if (topoNodes == null) string += "not possible";
        else for (int i = 0; i < topoNodes.length;i++) string += topoNodes[i] + " ";

        return string + "\n";
    }

    public String showDijkstras() {
        StringBuilder sb = new StringBuilder();

        for (int fromNode = 0; fromNode < graph.size();fromNode++) {

            sb.append("\n" + fromNode + "\nNode Forgj Dist\n");

            int[][] data = graph.dijkstra(fromNode);

            for (int toNode = 0; toNode < graph.size();toNode++) {

                sb.append( "   " + toNode);

                if (data[0][toNode] == 10000000) {
                    sb.append("     -    -");
                } else {
                    sb.append("     ");
                    if (fromNode == toNode) sb.append( " ");
                    else sb.append(data[1][toNode]);
                    sb.append("    " + data[0][toNode]);
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public String showDijkstras(int fromNode) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + fromNode + "\nNode Forgj Dist\n");

        int[][] data = graph.dijkstra(fromNode);

        for (int toNode = 0; toNode < graph.size();toNode++) {

            sb.append( "   " + toNode);

            if (data[0][toNode] == 10000000) {
                sb.append("     -    -");
            } else {
                sb.append("     ");
                if (fromNode == toNode) sb.append( " ");
                else sb.append(data[1][toNode]);
                sb.append("    " + data[0][toNode]);
            }
            sb.append("\n");
        }


        return sb.toString();
    }

    public String showPrims() {

        String string = "";
        int[][] data = graph.prims(0);
        int sum = 0;
        string += "Kanter:\nFra - Til   Vekt\n";
        for (int i = 0; i < data[0].length;i++) {
            if (data[0][i] > -1) {
                string += "  " + data[0][i] + " - " + i + "        " + data[1][i] + "\n";
                sum += data[1][i];
            }
        }
        string += "Sum vekter:    " + sum + "\n\n";

        return string;
    }

    public String getStartLog() {
        return startLog;
    }
}
