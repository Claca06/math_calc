package it.nsa.matematica.calcoli_matematici;

import it.nsa.matematica.calcoli_matematici.type.IntegerUtils;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        Long[] numbers = createArray(100000);
        Long[] numbers = new Long[5];
        numbers[0] = 18L;
        numbers[1] = 31L;
        numbers[2] = 30L;
        numbers[3] = 12L;
        numbers[4] = 120L;

        long startNew = System.currentTimeMillis();
        System.out.println(IntegerUtils.resultOfForFibonacciIterations(14));
        System.out.println(System.currentTimeMillis() - startNew);
    }

    private static Long[] createArray(int maxValue) {
        Long[] array = new Long[maxValue];
        Random random = new Random();
        for (int i = 0; i < maxValue; i++) {
            array[i] = random.nextLong();
        }
        return array;
    }
}
