package it.nsa.matematica.calcoli_matematici;

import it.nsa.matematica.calcoli_matematici.utility.SafeExecutor;

import java.math.BigInteger;

import static it.nsa.matematica.calcoli_matematici.type.Integer.*;

public class CalcoliMatematiciApplicationTest {
    public static void main(String[] args) {
        // Variabili per i calcoli
        Long[] valuesForSum = {4L, 7L};
        Long[] valuesForProduct = {3L, 7L};
        Long valueForFactorial = 5L;
        Long[] valuesForDifference = {8L, 6L, 3L, 5L, 8L};
        Long[] valuesForDivision = {12L, 6L, 2L};
        Long[] valuesForSumOfSquares = {3L, 6L, 2L};
        Long[] valuesForAverage = {3L, 6L, 5L, 2L};
        Long[] valuesForMaximum = {-50L, 6L, -12L, 3L, 0L};
        Long[] valuesForMinimum = {-50L, 6L, 12L, 3L, 0L};
        Long percentageForPercent = 65L;
        Long totalForPercent = 400L;
        Long valueForSquareRoot = 63L;
        Long valueForFibonacci = 25L;
        int fibonacciIterations = 8;

        // Esecuzione e stampa dei risultati
        executeAndPrint(() -> sumOf(valuesForSum), "sum of " + arrayToString(valuesForSum));
        executeAndPrint(() -> productOf(valuesForProduct), "product of " + arrayToString(valuesForProduct));
        executeAndPrint(() -> factorialOf(valueForFactorial), "factorial of " + valueForFactorial);
        executeAndPrint(() -> differenceOf(valuesForDifference), "difference of " + arrayToString(valuesForDifference));
        executeAndPrint(() -> divisionOf(valuesForDivision), "division of " + arrayToString(valuesForDivision));
        executeAndPrint(() -> sumOfSquaresOf(valuesForSumOfSquares), "sum of squares of " + arrayToString(valuesForSumOfSquares));
        executeAndPrint(() -> averageOf(valuesForAverage), "average of " + arrayToString(valuesForAverage));
        executeAndPrint(() -> percentOf(percentageForPercent, totalForPercent), percentageForPercent + "% of " + totalForPercent);
        executeAndPrint(() -> percentageOf(totalForPercent, percentageForPercent), percentageForPercent + "parts of " + totalForPercent);
        executeAndPrint(() -> maximumOf(valuesForMaximum), "maximum of " + arrayToString(valuesForMaximum));
        executeAndPrint(() -> minimumOf(valuesForMinimum), "minimum of " + arrayToString(valuesForMinimum));
        executeAndPrint(() -> squareRootOf(valueForSquareRoot), "square root of " + valueForSquareRoot);
        executeAndPrint(() -> resultOfForFibonacciIterations(valueForFibonacci, fibonacciIterations),
                "result of fibonacci iterations starting from " + valueForFibonacci + " with " + fibonacciIterations + " iterations");
    }

    // Metodo generico per eseguire un'operazione e stampare il risultato
    private static void executeAndPrint(SafeExecutor.Operation operation, String description) {
        BigInteger result = SafeExecutor.execute(operation);
        System.out.println(description + ": " + result);
    }

    // Metodo per convertire un array in stringa leggibile
    private static String arrayToString(Long[] values) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
