package Exceptions;

public class FirstLoginException extends RuntimeException {
    public FirstLoginException() {
        super("Password predefinita ancora impostata. Cambiarla.");
    }
}
