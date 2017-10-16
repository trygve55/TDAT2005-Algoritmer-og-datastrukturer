package com.trygve.oving8;

/**
 * DO NOT USE FOR BIG DATASETS
 */
public class Naboliste2 {
    int[] indexTable, connectionTable;

    public Naboliste2(int nodes, int connections) {
        indexTable = new int[nodes + 1];
        connectionTable = new int[connections];
    }

    public void addConnection(int index, int connectionTo) {
        for (int i = connectionTable.length - 1 ; i > indexTable[index + 1];i--) {
            connectionTable[i] = connectionTable[i-1];
        }
        if (index + 1 < indexTable.length)connectionTable[indexTable[index + 1]] = connectionTo;
        for (int i = index + 1;i < indexTable.length;i++) indexTable[i]++;
    }

    public String toString() {
        String string = "";


        for (int i = 0; i < indexTable.length-1;i++) {
            string += "from: " + i + " to: ";
            for (int j = indexTable[i];j < indexTable[i+1];j++) {
                string += connectionTable[j] + ", ";
            }
            string += "\n";
        }

        return string;
    }
}
