package it.nsa.matematica.calcoli_matematici.type;

import it.nsa.matematica.calcoli_matematici.exception.ApproximationException;
import it.nsa.matematica.calcoli_matematici.exception.MathOperationException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static it.nsa.matematica.calcoli_matematici.utility.Utility.*;

/**
 * Classe per eseguire operazioni matematiche su numeri interi rappresentati come {@link BigInteger}.
 */
public class Integer {

    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger HUNDRED = BigInteger.valueOf(100);

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
     * Calcola il fattoriale del valore fornito.
     *
     * @param value Il valore di cui calcolare il fattoriale. Deve essere maggiore o uguale a zero.
     * @return Il fattoriale del valore come {@link BigInteger}.
     * @throws MathOperationException Se il valore fornito è negativo.
     */
    public static BigInteger factorialOf(Long value) {
        checkNegative(value);

        BigInteger result = ONE;
        for (long i = 1; i <= value; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

    /**
     * Calcola il valore minimo tra quelli forniti.
     *
     * @param values I valori tra cui trovare il minimo. Devono contenere almeno un elemento e non devono essere null.
     * @return Il valore minimo come {@link BigInteger}.
     * @throws MathOperationException Se i valori sono null o vuoti.
     */
    public static BigInteger minimumOf(Long... values) {
        checkLength(values);
        checkValues(values);

        BigInteger min = BigInteger.valueOf(values[0]);
        for (Long value : values) {
            BigInteger current = BigInteger.valueOf(value);
            if (current.compareTo(min) < 0) {
                min = current;
            }
        }

        return min;
    }


    /**
     * Calcola il valore massimo tra quelli forniti.
     *
     * @param values I valori tra cui trovare il massimo. Devono contenere almeno un elemento e non devono essere null.
     * @return Il valore massimo come {@link BigInteger}.
     * @throws MathOperationException Se i valori sono null o vuoti.
     */
    public static BigInteger maximumOf(Long... values) {
        checkLength(values);
        checkValues(values);

        BigInteger max = BigInteger.valueOf(values[0]);
        for (Long value : values) {
            BigInteger current = BigInteger.valueOf(value);
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }

        return max;
    }

    /**
     * Calcola la media aritmetica dei valori forniti.
     *
     * @param values I valori di cui calcolare la media. Devono contenere almeno un elemento e non devono essere null.
     * @return La media aritmetica come {@link BigInteger} (arrotondata per difetto).
     * @throws MathOperationException Se i valori sono null o vuoti.
     */
    public static BigInteger averageOf(Long... values) {
        checkLength(values);
        checkValues(values);

        BigInteger sum = ZERO;
        for (Long value : values) {
            sum = sum.add(BigInteger.valueOf(value));
        }

        return sum.divide(BigInteger.valueOf(values.length));
    }

    /**
     * Calcola il risultato della division dei valori forniti per un certo numero.
     *
     * @param prcntVal Valore intero rappresentativo della percentuale.
     * @param total    Valore totale da cui calcolare la percentuale.
     * @return Il risultato della division come {@link BigInteger}
     * @throws MathOperationException Se i valori sono negativi.
     */
    public static BigInteger percentOf(Long prcntVal, Long total) {
        checkNegative(total);
        checkNegative(prcntVal);
        return BigInteger.valueOf(prcntVal).multiply(BigInteger.valueOf(total)).divide(HUNDRED);
    }

    /**
     * Calcola il risultato percentuale di un valore rispetto al totale, approssimando correttamente.
     * L'approssimazione avviene arrotondando per eccesso dal .5 in su e per difetto sotto .5.
     *
     * @param total Valore totale da cui calcolare la percentuale.
     * @param part Valore parte del totale, poi tradotto in valore percentuale.
     * @return Il risultato percentuale come {@link BigInteger}, arrotondato correttamente.
     * @throws MathOperationException Se i valori sono negativi o se il totale è zero.
     */
    /**
     * Calcola il risultato percentuale di una parte rispetto al totale,
     * approssimando per eccesso dal .5 in su e per difetto sotto il .5.
     *
     * @param total Valore totale da cui calcolare la percentuale.
     * @param part  Valore parte del totale, poi tradotto in valore percentuale.
     * @return Il risultato della percentuale come {@link BigInteger}.
     * @throws MathOperationException Se i valori sono negativi o il totale è zero.
     */
    public static BigInteger percentageOf(Long total, Long part) {
        checkZeroValue(total);
        checkNegative(total);
        checkNegative(part);

        BigDecimal rawPercentage = BigDecimal.valueOf(part)
                .divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        return rawPercentage.setScale(0, RoundingMode.HALF_UP).toBigInteger();
    }


    /**
     * Calcola il risultato della sequenza di Fibonacci fino all'iterazione specificata.
     *
     * @param startingNumber     L'iterazione per cui calcolare la sequenza di Fibonacci. Deve essere maggiore o uguale a zero.
     * @param numberOfIterations Il numero di iterazioni fibonacci che il metodo calcolerà dal numero di partenza.
     * @return Il valore della sequenza di Fibonacci come {@link BigInteger}.
     * @throws MathOperationException Se il valore fornito è negativo.
     */
    public static BigInteger resultOfForFibonacciIterations(Long startingNumber, java.lang.Integer numberOfIterations) {
        checkNegative(startingNumber);

        BigInteger a = BigInteger.valueOf(startingNumber);
        BigInteger b = BigInteger.valueOf(startingNumber).multiply(BigInteger.valueOf(8)).divide(BigInteger.valueOf(5));

        if (startingNumber == 1) {
            return b;
        }

        for (int i = 2; i <= numberOfIterations; i++) {
            BigInteger temp = a;
            a = b;
            b = temp.add(b);
        }

        return a;
    }
}