package com.trygve.oving11;

import org.apache.commons.lang.ArrayUtils;

import java.util.stream.IntStream;

/**
 * Created by Trygve on 06.11.2017.
 */
public class Automat {
    char[] acceptedInput;
    int[] acceptedEndStates;
    int[][] nextStateMatrix;

    public Automat(char[] acceptedInput, int[] acceptedEndStates, int[][] nextStateMatrix) {
        if (acceptedInput.length != nextStateMatrix.length) throw new IllegalArgumentException("AcceptedInput and nextStateMatrix is not same length.");

        this.acceptedInput = acceptedInput;
        this.acceptedEndStates = acceptedEndStates;
        this.nextStateMatrix = nextStateMatrix;
    }

    public boolean sjekkInput(char[] input) {
        int inputIndex;
        int[] inputArray = new int[input.length];
        for (int i = 0; i < input.length;i++) {
            inputIndex = -1;
            for (int j = 0; j < acceptedInput.length;j++) {
                if (input[i] == acceptedInput[j]) inputIndex = j;
            }
            if (inputIndex == -1) throw new IllegalArgumentException("Illegal input");
            inputArray[i] = inputIndex;
        }

        int state = 0;
        for (int i = 0; i < inputArray.length;i++) {
            state = nextStateMatrix[inputArray[i]][state];
        }

        return ArrayUtils.contains(acceptedEndStates, state);
    }
}
