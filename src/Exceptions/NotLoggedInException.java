package Exceptions;

public class NotLoggedInException extends RuntimeException {
    public NotLoggedInException(){
        super("Login non effettuato.");

    }
}
