package supermercado.controller.exceptions;

public class EntidadeInexistente extends Exception {
    public EntidadeInexistente(String message, Throwable cause) {
        super(message, cause);
    }
    public EntidadeInexistente(String message) {
        super(message);
    }
}
