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
        if(this.password.equals(passOld))
        {
            if(!(this.password.equals(passNew))){
                this.password=passNew;
                this.defaultPassword=false;
                return true;
            }else{
                return false;       //password scelta uguale a quella gia' impostata
            }
        }else{
           return false;        //errore conferma vcchia password
        }
    }

    public boolean logIn(String pass){
        if(defaultPassword)
        {
            return false;   //password ancora di default
        }else{
            if(this.password.equals(pass)){
                return loggedIn=true; //password corretta

            }else{
                return false;   //password errata
            }
        }
    }

    @Override
     public boolean operazione(double qta){
        if(loggedIn){
            if(qta>0){
                saldo+=qta;
                return true;
            }else{
                if(this.saldo>(-qta)){
                    saldo+=qta;
                    return true;
                }else{
                    return false;   //saldo insufficiente per il prelievo
                }
            }
        }else{
            return false;    //utente non loggato
        }
    }
}
