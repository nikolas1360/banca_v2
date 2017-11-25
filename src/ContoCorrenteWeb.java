import Exceptions.*;

public class ContoCorrenteWeb extends ContoCorrente {
    private boolean loggedIn;
    private boolean defaultPassword;
    private String password;
    public ContoCorrenteWeb(String cf, String iban) {
        super(cf,iban);
        this.loggedIn=false;
        this.defaultPassword=true;
        this.password="changeme";
    }

    public boolean changePassword(String passOld, String passNew){
        try{
            checkPassword(passOld);
            checkNewPassword(passNew);
            this.password=passNew;
            this.defaultPassword=false;
            return true;

        }catch (LoginFailedException exc1) {
            System.err.println(exc1.getMessage() + "vecchia password errata (impossibile cambiarla).");
        }catch (ChangePassException exc2) {
            System.err.println(exc2.getMessage() + "la nuova password è uguale alla vecchia");
        }
        return false;

    }

    public boolean logIn(String pass){
        try{
            checkFirstLogin();
            checkPassword(pass);
            return loggedIn=true;


        }catch(LoginFailedException exc1){
            System.err.println(exc1.getMessage() + "password errata");

        }
        catch( FirstLoginException exc2){
            System.err.println(exc2.getMessage());

        }
        return false;
    }

    @Override
    public boolean operazione(double qta){
        try{
            checkLogIn();
            checkSaldo(qta);
            saldo+=qta;
            return true;
        }catch(NotLoggedInException exc1){
            System.err.println(exc1.getMessage());

        }catch (OperationErrorException exc2){
            System.err.println(exc2.getMessage());
        }
        return false;
    }

    private void checkLogIn(){
         if(!(loggedIn)){
             throw new NotLoggedInException();
         }
    }
    private void checkFirstLogin(){
        if(this.defaultPassword){
            throw new LoginFailedException();
        }


    }
    private void checkPassword(String password){
        if(!(this.password.equals(password))){
            throw  new LoginFailedException();
        }


    }
    private void checkNewPassword( String passNew){
        if(this.password.equals(passNew)){
            throw new ChangePassException();
        }

    }

}


