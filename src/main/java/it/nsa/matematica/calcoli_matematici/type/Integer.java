package it.nsa.matematica.calcoli_matematici.type;

import it.nsa.matematica.calcoli_matematici.exception.MathOperationException;
import it.nsa.matematica.calcoli_matematici.exception.ApproximationException;

import java.math.BigInteger;

import static it.nsa.matematica.calcoli_matematici.utility.Utility.*;

/**
 * Classe per eseguire operazioni matematiche su numeri interi rappresentati come {@link BigInteger}.
 */
public class Integer {

    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger ZERO = BigInteger.ZERO;

    /**
     * Calcola la somma di tutti i valori forniti.
     *
     * @param values I valori da sommare. Devono contenere almeno due elementi e non devono essere null.
     * @return La somma di tutti i valori come {@link BigInteger}.
     * @throws MathOperationException Se i valori sono null, vuoti o contengono valori non validi.
     */
    public static BigInteger sumOf(Long... values) {
        checkLength(values);
        checkValues(values);
        BigInteger sum = ZERO;
        for (Long value : values) {
            sum = sum.add(BigInteger.valueOf(value));
        }
        return sum;
    }

    /**
     * Calcola la differenza dei valori forniti in ordine di inserimento.
     *
     * @param values I valori da cui calcolare la differenza. Devono contenere almeno due elementi e non devono essere null.
     * @return La differenza tra i valori come {@link BigInteger}.
     * @throws MathOperationException Se i valori sono null, vuoti o contengono valori non validi.
     */
    public static BigInteger differenceOf(Long... values) {
        checkLength(values);
        checkValues(values);

        BigInteger result = BigInteger.valueOf(values[0]);

        for (int i = 1; i < values.length; i++) {
            BigInteger current = BigInteger.valueOf(values[i]);
            result = result.subtract(current);
        }

        return result;
    }

    /**
     * Calcola il prodotto di tutti i valori forniti.
     *
     * @param values I valori da moltiplicare. Devono contenere almeno due elementi e non devono essere null.
     * @return Il prodotto di tutti i valori come {@link BigInteger}.
     * @throws MathOperationException Se i valori sono null, vuoti o contengono valori non validi.
     */
    public static BigInteger productOf(Long... values) {
        checkLength(values);
        BigInteger product = ONE;

        for (Long value : values) {
            product = product.multiply(BigInteger.valueOf(value));
        }
        return product;
    }

    /**
     * Calcola la divisione dei valori forniti in ordine di inserimento.
     *
     * @param values I valori da dividere. Devono contenere almeno due elementi e non devono essere null.
     * @return Il risultato della divisione come {@link BigInteger}.
     * @throws MathOperationException Se uno dei divisori è zero o la divisione non produce un numero intero.
     */
    public static BigInteger divisionOf(Long... values) {
        checkLength(values);
        checkValues(values);

        BigInteger result = BigInteger.valueOf(values[0]);

        for (int i = 1; i < values.length; i++) {
            Long value = values[i];
            checkZeroValue(value);
            BigInteger currentValue = BigInteger.valueOf(value);

            if (!result.mod(currentValue).equals(ZERO)) {
                throw new MathOperationException("La divisione di " + result + " per " + currentValue + " non è un numero intero.");
            }

            result = result.divide(currentValue);
        }

        return result;
    }

    /**
     * Calcola la somma dei quadrati dei valori forniti.
     *
     * @param values I valori da cui calcolare i quadrati. Devono contenere almeno due elementi e non devono essere null.
     * @return La somma dei quadrati dei valori come {@link BigInteger}.
     * @throws MathOperationException Se i valori sono null, vuoti o contengono valori non validi.
     */
    public static BigInteger sumOfSquaresOf(Long... values) {
        checkLength(values);
        checkValues(values);

        BigInteger sum = BigInteger.ZERO;
        for (Long value : values) {
            BigInteger square = BigInteger.valueOf(value).pow(2);
            sum = sum.add(square);
        }
        return sum;
    }

    /**
     * Calcola la radice quadrata intera del valore fornito.
     *
     * @param value Il valore di cui calcolare la radice quadrata. Deve essere maggiore di zero.
     * @return La radice quadrata intera come {@link BigInteger}.
     * @throws ApproximationException Se il numero non è un quadrato perfetto. L'eccezione contiene il valore approssimativo.
     */
    public static BigInteger squareRootOf(Long value) {
        checkZeroValue(value);
        checkNegative(value);

        BigInteger n = BigInteger.valueOf(value);
        BigInteger a = BigInteger.ONE;
        BigInteger b = n.add(BigInteger.ONE).shiftRight(1);

        while (b.compareTo(a) >= 0) {
            BigInteger mid = a.add(b).shiftRight(1);
            BigInteger midSquared = mid.multiply(mid);

            if (midSquared.compareTo(n) > 0) {
                b = mid.subtract(BigInteger.ONE);
            } else if (midSquared.compareTo(n) < 0) {
                a = mid.add(BigInteger.ONE);
            } else {
                return mid;
            }
        }

        throw new ApproximationException("Il numero non è un quadrato perfetto. Approssimazione: " + a, a);
    }

    /**
     * Calcola il risultato della sequenza di Fibonacci fino all'iterazione specificata.
     *
     * @param value L'iterazione per cui calcolare la sequenza di Fibonacci. Deve essere maggiore o uguale a zero.
     * @return Il valore della sequenza di Fibonacci come {@link BigInteger}.
     * @throws MathOperationException Se il valore fornito è negativo.
     */
    public static BigInteger resultOfFibonacciIterations(Long value) {
        checkNegative(value);

        BigInteger a = ZERO;
        BigInteger b = ONE;

        if (value == 1) {
            return b;
        }

        for (int i = 2; i <= value; i++) {
            BigInteger temp = a;
            a = b;
            b = temp.add(b);
        }

        return a;
    }
}