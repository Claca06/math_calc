package it.nsa.matematica.calcoli_matematici.utility;

import java.math.BigInteger;

import static it.nsa.matematica.calcoli_matematici.utility.Utility.buildMessage;

/**
 * Classe utilitaria per eseguire operazioni matematiche in modo sicuro.
 * Questa classe fornisce un metodo centralizzato per eseguire operazioni
 * che possono sollevare eccezioni, catturandole e gestendole automaticamente.
 * <p>
 * Le eccezioni vengono gestite internamente e un valore di fallback (di default {@link BigInteger#ZERO})
 * viene restituito in caso di errore.
 */
public class SafeExecutor {

    /**
     * Interfaccia funzionale che rappresenta un'operazione matematica sicura.
     * Le implementazioni di questa interfaccia definiscono un'operazione che
     * restituisce un valore di tipo {@link BigInteger} e possono sollevare eccezioni.
     */
    @FunctionalInterface
    public interface Operation {
        /**
         * Esegue l'operazione.
         *
         * @return Il risultato dell'operazione come {@link BigInteger}.
         * @throws Exception Se si verifica un errore durante l'esecuzione dell'operazione.
         */
        BigInteger execute() throws Exception;
    }

    /**
     * Esegue l'operazione specificata in modo sicuro.
     * Se l'operazione solleva un'eccezione, questa viene gestita internamente e
     * viene restituito un valore di fallback (di default {@link BigInteger#ZERO}).
     *
     * @param operation L'operazione da eseguire, implementata come una lambda o un riferimento a un metodo.
     * @return Il risultato dell'operazione come {@link BigInteger}, oppure {@link BigInteger#ZERO} in caso di errore.
     */
    public static BigInteger execute(Operation operation) {
        try {
            return operation.execute();
        } catch (Exception e) {
            // Gestione dell'eccezione e ritorno di un valore predefinito (es. ZERO)
            System.err.println(buildMessage("Errore ricevuto: {}", e.getMessage()));
            return BigInteger.ZERO;
        }
    }
}
