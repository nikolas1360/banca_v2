package Exceptions;

public class NotWebException extends RuntimeException {
    public NotWebException(){
        super("Il conto non è di tipo web");
    }
}
