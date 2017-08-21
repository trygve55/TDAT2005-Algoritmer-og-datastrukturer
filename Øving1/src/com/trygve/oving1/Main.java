package com.trygve.oving1;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    //int[] priceChange = new int[]{-1, 3, -9, 2, 2, -1, 2, -1, -5};

        int[] priceChange = getRandomPrices(100000);
        benchmark(3, priceChange);
        //benchmark(3, priceChange);
        //benchmark(3, priceChange);

        priceChange = getRandomPrices(200000);
        benchmark(3, priceChange);
        //benchmark(3, priceChange);
        //benchmark(3, priceChange);

        //priceChange = getRandomPrices(100);
        //benchmark(1000000, priceChange);
        //benchmark(10000000, priceChange);
        //benchmark(100000000, priceChange);

    }

    private static int[] getRandomPrices(int totalDays) {
        int[] priceChange = new int[totalDays];
        Random random = new Random();
        for (int i = 0; i < totalDays;i++) {
            priceChange[i] = random.nextInt(20) - 10;
        }
        return priceChange;
    }

    private static void benchmark(int timesToRun, int[] priceChange) {

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < timesToRun; i++) {
            findBestDeal(priceChange);
        }

        System.out.println("Total days: " + priceChange.length + " Times ran: " + timesToRun + "\nTime used: " + (System.currentTimeMillis() - startTime) + " mS");
    }

    private static int[] findBestDeal(int[] priceChange) {

        int highestPriceDifference = 0;
        int bestBuyDay = -1;
        int bestSellDay = -1;

        for (int i = 0; i < priceChange.length;i++) {
            int priceDifference = 0;
            for (int j = i; j < priceChange.length;j++)
            {
                priceDifference += priceChange[j];
                //System.out.println("start day:" + i + ". Now: " + (j + 1) + " PriceDifference: " + priceDifference);
                if (priceDifference > highestPriceDifference) {
                    highestPriceDifference = priceDifference;
                    bestBuyDay = i;
                    bestSellDay = j + 1;
                }
            }
        }

        //if (highestPriceDifference != 0) System.out.println("No days with profit.");
        //else System.out.println("Best buy day: " + bestBuyDay + ". Best sell day: " + bestSellDay + ". Profit: " + highestPriceDifference);

        return new int[]{bestBuyDay, bestSellDay, highestPriceDifference};
    }

    private static int[] findBestDealN1(int[] priceChange) {

        int highestPriceDifference = 0;
        int bestBuyDay = -1;
        int bestSellDay = -1;

        for (int i = 0; i < priceChange.length;i++) {
            int priceDifference = 0;
            for (int j = i; j < priceChange.length;j++)
            {
                priceDifference += priceChange[j];
                //System.out.println("start day:" + i + ". Now: " + (j + 1) + " PriceDifference: " + priceDifference);
                if (priceDifference > highestPriceDifference) {
                    highestPriceDifference = priceDifference;
                    bestBuyDay = i;
                    bestSellDay = j + 1;
                }
            }
        }

        //if (highestPriceDifference != 0) System.out.println("No days with profit.");
        //else System.out.println("Best buy day: " + bestBuyDay + ". Best sell day: " + bestSellDay + ". Profit: " + highestPriceDifference);

        return new int[]{bestBuyDay, bestSellDay, highestPriceDifference};
    }
}
