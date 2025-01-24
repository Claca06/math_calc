package it.nsa.matematica.calcoli_matematici.exception;

public class Handler {

    /**
     * Gestisce le eccezioni stampando il messaggio di errore.
     *
     * @param e L'eccezione da gestire.
     */
    public static void handle(Exception e) {
        if (e instanceof ApproximationException) {
            System.err.println(e.getMessage());
        } else if (e instanceof MathOperationException) {
            System.err.println(e.getMessage());
        } else {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
