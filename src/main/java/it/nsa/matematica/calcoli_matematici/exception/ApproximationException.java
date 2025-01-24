package it.nsa.matematica.calcoli_matematici.exception;

import lombok.Getter;

import java.math.BigInteger;

/**
 * Eccezione personalizzata per indicare un'approssimazione durante il calcolo.
 */
@Getter
public class ApproximationException extends RuntimeException {

    private final BigInteger approximation;

    /**
     * Costruttore per creare l'eccezione con un messaggio personalizzato e un valore approssimato.
     *
     * @param message       Il messaggio che descrive l'errore.
     * @param approximation Il valore approssimato calcolato.
     */
    public ApproximationException(String message, BigInteger approximation) {
        super(message);
        this.approximation = approximation;
    }
}