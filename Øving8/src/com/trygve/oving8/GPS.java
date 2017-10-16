package com.trygve.oving8;

/**
 * Created by Trygve on 07.10.2017.
 */
public class GPS {
    GraphLinked graph;
    GraphLocation graphLocation;

    String startLog = "";

    public GPS(String filenameGraph, String filenameLocations) {
        long startTime = System.currentTimeMillis();
        graph = GraphFileReader.readGraphFile(filenameGraph);
        startLog += "Loaded graph file '" + filenameGraph + "' in " + (System.currentTimeMillis()-startTime) + "ms.\n";

        long startTime2 = System.currentTimeMillis();
        graphLocation = GraphFileReader.readGraphLocationFile(filenameLocations);
        startLog += "Loaded locations file '" + filenameLocations + "' in " + (System.currentTimeMillis()-startTime2) + "ms.\n";
    }

    public String navigate(String from, String to) {
        String string = "";
        long startTime = System.currentTimeMillis();
        int[] nodes = graph.bfs(graphLocation.getNode(from), graphLocation.getNode(to));
        string += "There are " + (nodes.length-1) + " nodes between " + from + " and " + to + ".\n";
        string += "Computation time " + (System.currentTimeMillis()-startTime) + " ms.";
        return string;
    }

    public String navigateD(String from, String to) {
        String string = "";
        long startTime = System.currentTimeMillis();
        int[][] nodes = graph.dijkstra(graphLocation.getNode(from), graphLocation.getNode(to));
        string += "There are " + (nodes[0].length-1) + " nodes between " + from + " and " + to + ".\n";
        string += "Time: " + Math.floor(nodes[1][0] / 6000.0) + " min (" + Math.floor(nodes[1][0] / 36000.0) / 10 + " hours)\n";
        string += "Computation time " + (System.currentTimeMillis()-startTime) + " ms.";

        string += "The route is: \n";
        for (int i = 0;i < nodes[0].length;i++) {
            String name = graphLocation.getName(nodes[0][i]);
            if (name != null) string += name + "\n";
        }

        return string;
    }

    public String getStartLog() {
        return startLog;
    }
}
