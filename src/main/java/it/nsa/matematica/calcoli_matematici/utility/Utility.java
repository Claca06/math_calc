package it.nsa.matematica.calcoli_matematici.utility;

/**
 * Classe di utility per eseguire controlli sui parametri forniti alle operazioni matematiche.
 */
public class Utility {

    /**
     * Costruisce un messaggio sostituendo i segnaposto "{}" con i parametri forniti.
     * * @param message Il messaggio con segnaposto "{}".
     *
     * @param params Gli oggetti da sostituire nei segnaposto.
     * @return Il messaggio costruito.
     */
    public static String buildMessage(String message, Object... params) {
        if (message == null || params == null || params.length == 0) {
            return message;
        }
        StringBuilder result = new StringBuilder();
        int placeholderIndex = 0;
        int paramIndex = 0;
        while (placeholderIndex < message.length()) {
            int nextPlaceholder = message.indexOf("{}", placeholderIndex);
            if (nextPlaceholder == -1) {
                result.append(message.substring(placeholderIndex));
                break;
            }
            result.append(message, placeholderIndex, nextPlaceholder);
            if (paramIndex < params.length) {
                result.append(params[paramIndex++]);
            } else {
                result.append("{}");
            }
            placeholderIndex = nextPlaceholder + 2;
        }
        return result.toString();
    }
}
