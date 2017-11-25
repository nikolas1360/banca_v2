package Exceptions;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(){super("Conto di tipo Deposito");}
    public InvalidOperationException(String message){super(message);}

}
