package com.trygve.oving7;

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

    public String getStartLog() {
        return startLog;
    }
}
