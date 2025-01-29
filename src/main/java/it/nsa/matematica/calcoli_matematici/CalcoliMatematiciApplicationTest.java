package it.nsa.matematica.calcoli_matematici;

import it.nsa.matematica.calcoli_matematici.utility.SafeExecutor;

import java.math.BigInteger;

import static it.nsa.matematica.calcoli_matematici.type.IntegerUtils.*;
import static it.nsa.matematica.calcoli_matematici.utility.Utility.buildMessage;

public class CalcoliMatematiciApplicationTest {
    public static void main(String[] args) {
        // Variabili per i calcoli
        Long[] valuesForSum = {4L, 7L, 19L};
        Long[] valuesForProduct = {3L, 7L, 5L};
        Long valueForFactorial = 5L;
        Long[] valuesForDifference = {8L, 6L, 3L, 5L, 8L};
        Long[] valuesForDivision = {200L, 10L, 5L, 2L};
        Long[] valuesForSumOfSquares = {3L, 6L, 2L};
        Long[] valuesForAverage = {3L, 6L, 5L, 2L};
        Long[] valuesForMaximum = {-50L, 6L, -12L, 3L, 0L};
        Long[] valuesForMinimum = {-50L, 6L, 12L, 3L, 0L};
        Long percentageForPercent = 65L;
        Long totalForPercent = 400L;
        Long valueForSquareRoot = 81L;
        int fibonacciIterations = 7;

        // Esecuzione e stampa dei risultati
        executeAndPrint(() -> sumOf(valuesForSum), buildMessage("sumOf {} ", arrayToString(valuesForSum)));
        executeAndPrint(() -> productOf(valuesForProduct), buildMessage("productOf {}", arrayToString(valuesForProduct)));
        executeAndPrint(() -> differenceOf(valuesForDifference), buildMessage("differenceOf {}", arrayToString(valuesForDifference)));
        executeAndPrint(() -> divisionOf(valuesForDivision), buildMessage("divisionOf {}", arrayToString(valuesForDivision)));
        executeAndPrint(() -> sumOfSquaresOf(valuesForSumOfSquares), buildMessage("sumOfSquaresOf {}", arrayToString(valuesForSumOfSquares)));
        executeAndPrint(() -> averageOf(valuesForAverage), buildMessage("averageOf {}", arrayToString(valuesForAverage)));
        executeAndPrint(() -> maximumOf(valuesForMaximum), buildMessage("maximumOf {}", arrayToString(valuesForMaximum)));
        executeAndPrint(() -> minimumOf(valuesForMinimum), buildMessage("minimumOf {}", arrayToString(valuesForMinimum)));
        executeAndPrint(() -> factorialOf(valueForFactorial), buildMessage("factorialOf {}", valueForFactorial));
        executeAndPrint(() -> percentOf(totalForPercent, percentageForPercent), buildMessage("{}% of {}", percentageForPercent, totalForPercent));
        executeAndPrint(() -> percentageOf(totalForPercent, percentageForPercent), buildMessage("percentageOf {} over {}", percentageForPercent, totalForPercent));
        executeAndPrint(() -> squareRootOf(valueForSquareRoot), buildMessage("squareRootOf {}", valueForSquareRoot));
        executeAndPrint(() -> resultOfForFibonacciIterations(fibonacciIterations),
                buildMessage("{} fibonacciIterations", fibonacciIterations));
    }

    // Metodo generico per eseguire un'operazione e stampare il risultato
    private static void executeAndPrint(SafeExecutor.Operation operation, String description) {
        BigInteger result = SafeExecutor.execute(operation);
        System.out.println(buildMessage("Risultato per {} = {}", description, result));
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
