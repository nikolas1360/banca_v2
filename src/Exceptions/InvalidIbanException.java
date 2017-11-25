package Exceptions;

public class InvalidIbanException extends RuntimeException {
    public InvalidIbanException(){
        super("Iban non trovato");
    }
    public InvalidIbanException(String message){
        super(message);
    }
}
