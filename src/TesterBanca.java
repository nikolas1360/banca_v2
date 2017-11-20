public class TesterBanca {
    public static void main(String[] args) {
        Banca b= new Banca("BPN");

        Conto corrente = b.aggiungiConto(TipoConto.CORRENTE,"CF1");
        Conto deposito = b.aggiungiConto(TipoConto.DEPOSITO,"CF2");
        Conto web = b.aggiungiConto(TipoConto.CORRENTEWEB,"CF3");

        corrente.operazione(100);

        deposito.operazione(500);


        //ACCOUNTABLES CONTO CORRENTE
        if(!(b.addAccountable("RADICE1",TipoAccountable.ADDEBITO,120.0))) {
            System.out.println("Impossibile aggiungere accountable.");
        }
        if(!(b.addAccountable("RADICE1",TipoAccountable.ACCREDITO,100.0))) {
            System.out.println("Impossibile aggiungere accountable.");
        }
        if(!(b.applicaAccountable("RADICE1"))){
            System.out.println("Impossibile applicare alcuni addebiti, saldo insufficiente.");
        }
        System.out.println(corrente.toString());

        //ACCOUNTABLES CONTO DEPOSITO
        if(!(b.addAccountable("RADICE2",TipoAccountable.ADDEBITO,120.0))) {
            System.out.println("Impossibile aggiungere accountable.");
        }
        if(!(b.addAccountable("RADICE2",TipoAccountable.ACCREDITO,100.0))) {
            System.out.println("Impossibile aggiungere accountable.");
        }
        if(!(b.applicaAccountable("RADICE2"))){
            System.out.println("Impossibile applicare alcuni addebiti, saldo insufficiente.");
        }
        System.out.println(deposito.toString());

        //GESTIONE LOGIN CONTO WEB
        if(b.changePassword("RADICE3","nuova","changeme")){
            System.out.println("Password cambiata.");
        }else{
            System.out.println("Errore cambio password");
        }
        if(b.login("RADICE3","nuova")){
            System.out.println("Login OK.");
        }else{
            System.out.println("Login KO");
        }
        web.operazione(100);
        System.out.println(web.toString());






    }
}
