package Exceptions;

public class ChangePassException extends RuntimeException {
    public ChangePassException(){
        super("Impossibile cambiare password: ");
    }
}
