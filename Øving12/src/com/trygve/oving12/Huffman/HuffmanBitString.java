package com.trygve.oving12.Huffman;

import java.util.BitSet;

/**
 * Created by Trygve on 06.11.2017.
 */
public class HuffmanBitString {
    private byte aByte;
    private BitSet bitSet;
    private int bitLength;
    private boolean newByte = false;

    public HuffmanBitString(byte aByte, BitSet bitSet, int bitLength) {
        this.aByte = aByte;
        this.bitSet = bitSet;
        this.bitLength = bitLength;
    }

    public HuffmanBitString(byte aByte, BitSet bitSet, int bitLength, boolean newByte) {
        this.aByte = aByte;
        this.bitSet = bitSet;
        this.bitLength = bitLength;
        this.newByte = newByte;
    }

    public byte getaByte() {
        return aByte;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public int length() {
        return bitLength;
    }

    public boolean isNewByte() {
        return newByte;
    }

    @Override
    public String toString() {
        String bit = "";
        for (int i = 0; i < bitLength; i++) {
            bit += ((bitSet.get(i)) ? '1' : '0');
        }

        return "HuffmanBitString{" +
                "aByte=" + (char)aByte +
                ", bitSet=" + bit +
                "}\n";
    }
}
