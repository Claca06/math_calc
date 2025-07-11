package it.nsa.matematica.calcoli_matematici.type;

import org.springframework.lang.Contract;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static java.math.BigInteger.*;

/**
 * Classe di utility per operazioni matematiche su numeri interi rappresentati come {@link BigInteger}.
 * <p>
 * Tutti i metodi restituiscono {@link BigInteger#ZERO} se i parametri in input sono nulli, insufficienti
 * o non validi secondo il minimo richiesto.
 */
public class IntegerUtils {

    private static final BigInteger HUNDRED = TEN.multiply(TEN);

    /**
     * Calcola la somma di tutti i valori forniti.
     *<p>
     * @param values I valori da sommare. Devono contenere almeno 2 elementi non nulli.
     * @return La somma come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger sumOf(Long... values) {
        if (notValid(values, 2)) return ZERO;
        return toBigIntegerStream(values)
                .reduce(ZERO, BigInteger::add);
    }

    /**
     * Calcola la differenza dei valori in ordine di inserimento (x1 - x2 - x3 ...).
     *<p>
     * @param values I valori da cui calcolare la differenza. Devono contenere almeno 2 elementi non nulli.
     * @return La differenza come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger differenceOf(Long... values) {
        if (notValid(values, 2)) return ZERO;
        return toBigIntegerStream(values)
                .reduce(BigInteger::subtract)
                .orElse(ZERO);
    }

    /**
     * Calcola il prodotto di tutti i valori forniti.
     *<p>
     * @param values I valori da moltiplicare. Devono contenere almeno 2 elementi non nulli.
     * @return Il prodotto come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger productOf(Long... values) {
        if (notValid(values, 2)) return ZERO;
        return toBigIntegerStream(values)
                .reduce(ONE, BigInteger::multiply);
    }

    /**
     * Calcola la divisione sequenziale dei valori (x1 / x2 / x3 ...).
     * Se un divisore è zero o la divisione non è intera, restituisce {@link BigInteger#ZERO}.
     *<p>
     * @param values I valori da dividere. Devono contenere almeno 2 elementi non nulli.
     * @return Il risultato come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger sequentialDivisionOf(Long... values) {
        if (notValid(values, 2)) return ZERO;
        return toBigIntegerStream(values)
                .reduce((acc, current) -> {
                    if (current.equals(ZERO) || !acc.mod(current).equals(ZERO)) {
                        return ZERO;
                    }
                    return acc.divide(current);
                })
                .orElse(ZERO);
    }

    /**
     * Calcola la somma dei quadrati dei valori forniti.
     *<p>
     * @param values I valori da elevare al quadrato. Devono contenere almeno 1 elemento non nullo.
     * @return La somma dei quadrati come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger sumOfSquaresOf(Long... values) {
        if (notValid(values, 1)) return ZERO;
        return toBigIntegerStream(values)
                .map(v -> v.pow(2))
                .reduce(ZERO, BigInteger::add);
    }

    /**
     * Calcola la radice quadrata intera (troncata) del valore fornito.
     *<p>
     * @param value Il valore di cui calcolare la radice. Deve essere non nullo.
     * @return La radice quadrata troncata come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger squareRootOf(Long value) {
        if (notValid(value)) return ZERO;
        return BigInteger.valueOf(value).sqrt();
    }

    /**
     * Calcola il fattoriale del valore fornito.
     *<p>
     * @param value Il valore di cui calcolare il fattoriale. Deve essere non nullo e >= 0.
     * @return Il fattoriale come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger factorialOf(Long value) {
        if (notValid(value) || value < 0) return ZERO;
        BigInteger result = ONE;
        for (long i = 1; i <= value; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Restituisce il valore minimo tra quelli forniti.
     *<p>
     * @param values I valori tra cui trovare il minimo. Devono contenere almeno 1 elemento non nullo.
     * @return Il minimo come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger minimumOf(Long... values) {
        if (notValid(values, 1)) return ZERO;
        return toBigIntegerStream(values)
                .reduce(BigInteger::min)
                .orElse(ZERO);
    }

    /**
     * Restituisce il valore massimo tra quelli forniti.
     *<p>
     * @param values I valori tra cui trovare il massimo. Devono contenere almeno 1 elemento non nullo.
     * @return Il massimo come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger maximumOf(Long... values) {
        if (notValid(values, 1)) return ZERO;
        return toBigIntegerStream(values)
                .reduce(BigInteger::max)
                .orElse(ZERO);
    }

    /**
     * Calcola la media aritmetica dei valori forniti (arrotondata per difetto).
     *<p>
     * @param values I valori da mediare. Devono contenere almeno 1 elemento non nullo.
     * @return La media come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger averageOf(Long... values) {
        if (notValid(values, 1)) return ZERO;
        return sumOf(values).divide(BigInteger.valueOf(values.length));
    }

    /**
     * Calcola il percentuale intero di un valore rispetto a 100.
     *<p>
     * @param total    Valore totale. Deve essere non nullo.
     * @param prcntVal Il valore percentuale. Se nullo, trattato come {@link BigInteger#ZERO}.
     * @return Il risultato come {@link BigInteger}, o {@link BigInteger#ZERO} se total non valido.
     */
    public static BigInteger percentOf(Long total, Long prcntVal) {
        if (notValid(total)) return ZERO;
        BigInteger percent = prcntVal != null ? BigInteger.valueOf(prcntVal) : ZERO;
        return BigInteger.valueOf(total)
                .divide(HUNDRED)
                .multiply(percent);
    }

    /**
     * Calcola la percentuale di part rispetto al totale,
     * arrotondando dal .5 in su per eccesso e sotto .5 per difetto.
     *<p>
     * @param total Valore totale. Deve essere non nullo e >0.
     * @param part  Valore parte. Deve essere non nullo e >=0.
     * @return Il risultato come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger percentageOf(Long total, Long part) {
        if (notValid(total) || total == 0 || notValid(part) || part < 0) return ZERO;
        BigDecimal totDec = BigDecimal.valueOf(total);
        BigDecimal partDec = BigDecimal.valueOf(part);
        return partDec
                .divide(totDec, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(HUNDRED))
                .setScale(0, RoundingMode.HALF_UP)
                .toBigInteger();
    }

    /**
     * Calcola il valore di Fibonacci per il numero di iterazioni specificato partendo da 0.
     *<p>
     * @param numberOfIterations Il numero di passi. Deve essere non nullo e >=0.
     * @return Il valore di Fibonacci come {@link BigInteger}, o {@link BigInteger#ZERO} se input non valido.
     */
    public static BigInteger resultOfForFibonacciIterations(Integer numberOfIterations) {
        if (numberOfIterations == null || numberOfIterations < 0) return ZERO;
        BigInteger a = ZERO;
        BigInteger b = ONE;
        for (int i = 1; i <= numberOfIterations; i++) {
            BigInteger temp = a;
            a = b;
            b = temp.add(b);
        }
        return a;
    }

    /* --- METODI AUSILIARI PRIVATI --- */

    /**
     * Verifica che l'array contenga almeno minRequired valori non nulli.
     */
    private static boolean notValid(Long[] values, int minRequired) {
        return values == null || toBigIntegerStream(values)
                .count() < minRequired;
    }

    /**
     * Verifica che il singolo valore non sia null.
     */
    private static boolean notValid(Long value) {
        return toBigIntegerStream(value).count() != 1;
    }

    /**
     * Converte un array di Long non nulli in Stream di BigInteger.
     */
    private static Stream<BigInteger> toBigIntegerStream(Long... values) {
        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .map(BigInteger::valueOf);
    }
}
