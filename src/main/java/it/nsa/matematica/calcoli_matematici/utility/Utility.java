package it.nsa.matematica.calcoli_matematici.utility;

import it.nsa.matematica.calcoli_matematici.exception.MathOperationException;

/**
 * Classe di utility per eseguire controlli sui parametri forniti alle operazioni matematiche.
 */
public class Utility {

    /**
     * Verifica che l'array fornito contenga almeno due elementi.
     *
     * @param values L'array di valori da controllare.
     * @throws MathOperationException Se l'array è nullo o contiene meno di due elementi.
     */
    public static void checkLength(Long[] values) {
        if (values == null || values.length < 2) {
            throw new MathOperationException("L'array deve contenere almeno due elementi.");
        }
    }

    /**
     * Verifica che l'array non contenga valori null.
     *
     * @param values L'array di valori da controllare.
     * @throws MathOperationException Se l'array contiene valori null.
     */
    public static void checkValues(Long[] values) {
        for (Long value : values) {
            if (value == null) {
                throw new MathOperationException("L'array non deve contenere valori null.");
            }
        }
    }

    /**
     * Verifica che il valore fornito non sia zero.
     *
     * @param value Il valore da controllare.
     * @throws MathOperationException Se il valore è zero.
     */
    public static void checkZeroValue(Long value) {
        if (value == 0) {
            throw new MathOperationException("Il valore non può essere zero.");
        }
    }

    /**
     * Verifica che il valore fornito non sia negativo.
     *
     * @param value Il valore da controllare.
     * @throws MathOperationException Se il valore è negativo.
     */
    public static void checkNegative(Long value) {
        if (value < 0) {
            throw new MathOperationException("Il valore non può essere negativo.");
        }
    }
}
