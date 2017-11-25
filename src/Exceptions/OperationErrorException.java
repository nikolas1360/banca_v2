package Exceptions;

public class OperationErrorException extends RuntimeException {
    public OperationErrorException(){
        super ("Saldo insufficiente.");
    }
}
