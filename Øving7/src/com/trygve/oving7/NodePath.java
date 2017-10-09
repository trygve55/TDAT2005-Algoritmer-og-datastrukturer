package com.trygve.oving7;

/**
 * Created by Trygve on 07.10.2017.
 */
public class NodePath {
    GraphLinked graph;

    String startLog = "";

    public NodePath(String filenameGraph) {
        long startTime = System.currentTimeMillis();
        graph = GraphFileReader.readGraphFile(filenameGraph);
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

    public String getStartLog() {
        return startLog;
    }
}
