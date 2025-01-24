package it.nsa.matematica.calcoli_matematici.exception;

/**
 * Eccezione personalizzata per gestire errori durante l'esecuzione di operazioni matematiche.
 */
public class MathOperationException extends RuntimeException {

    /**
     * Costruttore per creare l'eccezione con un messaggio personalizzato.
     *
     * @param message Il messaggio che descrive l'errore.
     */
    public MathOperationException(String message) {
        super(message);
    }

    /**
     * Costruttore per creare l'eccezione con un messaggio e una causa.
     *
     * @param message Il messaggio che descrive l'errore.
     * @param cause   La causa dell'errore.
     */
    public MathOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}