package Exceptions;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(){
        super("Login Fallito: ");
    }
}
