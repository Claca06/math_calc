package it.nsa.matematica.calcoli_matematici.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * Classe per eseguire operazioni matematiche su numeri interi rappresentati come {@link BigInteger}.
 */
public class IntegerUtils {

    private static final BigInteger HUNDRED = BigInteger.valueOf(100);

    /**
     * Calcola la somma di tutti i valori forniti.
     *
     * @param values I valori da sommare. Devono contenere almeno due elementi e non devono essere null.
     * @return La somma di tutti i valori come {@link BigInteger}.
     */
    public static BigInteger sumOf(Long... values) {
        return Optional.of(values)
                .map(_values -> Arrays.stream(_values)
                        .filter(Objects::nonNull)
                        .map(BigInteger::valueOf)
                        .reduce(ZERO, BigInteger::add)
                )
                .orElse(ZERO);

    }

    /**
     * Calcola la differenza dei valori forniti in ordine di inserimento.
     *
     * @param values I valori da cui calcolare la differenza. Devono contenere almeno due elementi e non devono essere null.
     * @return La differenza tra i valori come {@link BigInteger}.
     */
    public static BigInteger differenceOf(Long... values) {
        return Optional.of(values)
                .map(_values -> Arrays.stream(_values)
                        .filter(Objects::nonNull)
                        .map(BigInteger::valueOf)
                        .reduce(ZERO, BigInteger::subtract)
                )
                .orElse(ZERO);
    }

    /**
     * Calcola il prodotto di tutti i valori forniti.
     *
     * @param values I valori da moltiplicare. Devono contenere almeno due elementi e non devono essere null.
     * @return Il prodotto di tutti i valori come {@link BigInteger}.
     */
    public static BigInteger productOf(Long... values) {
        return Optional.of(values)
                .map(_values -> Arrays.stream(_values)
                        .filter(Objects::nonNull)
                        .map(BigInteger::valueOf)
                        .reduce(ONE, BigInteger::multiply)
                )
                .orElse(ZERO);
    }

    /**
     * Calcola la divisione dei valori forniti in ordine di inserimento.
     *
     * @param values I valori da dividere. Devono contenere almeno due elementi e non devono essere null.
     * @return Il risultato della divisione come {@link BigInteger}.
     */
    public static BigInteger divisionOf(Long... values) {
        return Optional.ofNullable(values)
                .filter(v -> v.length >= 2)
                .map(v -> Arrays.stream(v)
                        .filter(Objects::nonNull)
                        .map(BigInteger::valueOf)
                        .reduce((acc, current) -> {
                            if (current.equals(ZERO)) {
                                return ZERO;
                            }
                            if (!acc.mod(current).equals(ZERO)) {
                                return ZERO;
                            }
                            return acc.divide(current);
                        })
                        .orElse(ZERO)
                )
                .orElse(ZERO);
    }

    public static BigInteger sumOfSquaresOf(Long... values) {
        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .map(value -> BigInteger.valueOf(value).pow(2))
                .reduce(ZERO, BigInteger::add);
    }

    /**
     * Calcola la radice quadrata intera del valore fornito.
     *
     * @param value Il valore di cui calcolare la radice quadrata. Deve essere maggiore di zero.
     * @return La radice quadrata intera come {@link BigInteger}.
     */
    public static BigInteger squareRootOf(Long value) {
        return BigInteger.valueOf(value).sqrt();
    }

    /**
     * Calcola il fattoriale del valore fornito.
     *
     * @param value Il valore di cui calcolare il fattoriale. Deve essere maggiore o uguale a zero.
     * @return Il fattoriale del valore come {@link BigInteger}.
     */
    public static BigInteger factorialOf(Long value) {
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
     */
    public static BigInteger minimumOf(Long... values) {

        return Optional.of(values)
                .map(_values -> Arrays.stream(_values)
                        .filter(Objects::nonNull)
                        .map(BigInteger::valueOf)
                        .reduce(ONE, BigInteger::min)
                )
                .orElse(ZERO);
    }


    /**
     * Calcola il valore massimo tra quelli forniti.
     *
     * @param values I valori tra cui trovare il massimo. Devono contenere almeno un elemento e non devono essere null.
     * @return Il valore massimo come {@link BigInteger}.
     */
    public static BigInteger maximumOf(Long... values) {
        return Optional.of(values)
                .map(_values -> Arrays.stream(_values)
                        .filter(Objects::nonNull)
                        .map(BigInteger::valueOf)
                        .reduce(ONE, BigInteger::max)
                )
                .orElse(ZERO);
    }

    /**
     * Calcola la media aritmetica dei valori forniti.
     *
     * @param values I valori di cui calcolare la media. Devono contenere almeno un elemento e non devono essere null.
     * @return La media aritmetica come {@link BigInteger} (arrotondata per difetto).
     */
    public static BigInteger averageOf(Long... values) {
        return sumOf(values).divide(BigInteger.valueOf(values.length));
    }

    /**
     * Calcola il risultato della division dei valori forniti per un certo numero.
     *
     * @param total    Valore totale da cui calcolare la percentuale.
     * @param prcntVal Valore intero rappresentativo della percentuale.
     * @return Il risultato della division come {@link BigInteger}
     */
    public static BigInteger percentOf(Long total, Long prcntVal) {
        return BigInteger.valueOf(prcntVal).multiply(BigInteger.valueOf(total)).divide(HUNDRED);
    }

    /**
     * Calcola il risultato percentuale di una parte rispetto al totale,
     * approssimando per eccesso dal .5 in su e per difetto sotto il .5.
     *
     * @param total Valore totale da cui calcolare la percentuale.
     * @param part  Valore parte del totale, poi tradotto in valore percentuale.
     * @return Il risultato della percentuale come {@link BigInteger}.
     */
    public static BigInteger percentageOf(Long total, Long part) {

        return BigDecimal.valueOf(part)
                .divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_UP).toBigInteger();

    }


    /**
     * Calcola il risultato della sequenza di Fibonacci fino all'iterazione specificata.
     *
     * @param numberOfIterations Il numero di iterazioni fibonacci che il metodo calcolerà dal numero di partenza.
     * @return Il valore della sequenza di Fibonacci come {@link BigInteger}.
     */
    public static BigInteger resultOfForFibonacciIterations(Integer numberOfIterations) {

        BigInteger a = ZERO;
        BigInteger b = ONE;

        for (int i = 1; i <= numberOfIterations; i++) {
            BigInteger temp = a;
            a = b;
            b = temp.add(b);
        }

        return a;
    }
}