package com.trygve.oving12.RunLength;

/**
 * Created by Trygve on 07.11.2017.
 */
public class RunLength {

    public static byte[] encode(byte[] inputData) {
        int compressedFor = 0;
        int uncompressedFor = 0;
        byte lastByte = inputData[0];

        for (int i = 1; i < inputData.length; i++) {
            if (lastByte == inputData[i]) {
                compressedFor++;
                if (uncompressedFor > 1) {
                    System.out.println("non repeating for " + uncompressedFor);
                    compressedFor = 1;
                }
                uncompressedFor = 1;
            } else {
                uncompressedFor++;

                if (compressedFor > 1) {
                    System.out.println("repeating for " + compressedFor);
                    uncompressedFor = 1;
                }
                compressedFor = 1;

                lastByte = inputData[i];
            }
        }

        return null;
    }

    public static byte[] decode(byte[] inputData) {
        return null;
    }

    public static void main(String[] arg) {

        char[] test = (("Heeeeeeelllllooo").toCharArray());
        byte[] inputData = new byte[test.length];


        for (int i = 0; i < test.length; i++) inputData[i] = (byte) test[i];

        encode(inputData);
    }
}
