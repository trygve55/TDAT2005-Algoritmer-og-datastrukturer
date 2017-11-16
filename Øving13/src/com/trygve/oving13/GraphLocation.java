package com.trygve.oving13;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trygve on 07.10.2017.
 */
public class GraphLocation {
    private Map<Integer, String> locations = new HashMap<>();

    public void addLocation(int node, String name) {
        locations.put(node, name);
    }

    public int getNode(String name) {
        for (Map.Entry<Integer, String> entry : locations.entrySet()) {
            if (name.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public String getName(int node) {
        return locations.get(node);
    }
}
