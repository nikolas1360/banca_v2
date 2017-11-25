public class TesterBanca {
    public static void main(String[] args) {
        String str;
        Banca b= new Banca("BPN");
        Conto c1 = b.aggiungiConto(TipoConto.CORRENTEWEB,"CF1");
        b.login("RADICE1","changeme");                                     //iban non trovato
        b.changePassword("RADICE1","ciaociao","changeme4");
        b.changePassword("RADICE1","ciaociao","changeme");
        b.login("RADICE1","ciaociao");
        b.operazione("RADICE1",20);
        str = c1.toString();
        System.out.println(str);
        //b.operazione("RADICE1",-50);                                        //Saldo insufficiente
        //Accountable a1 = new Accountable(TipoAccountable.ADDEBITO, 55);
        //Accountable a2 = new Accountable(TipoAccountable.ACCREDITO, 160);
        /*b.addAccountable("RADICE1",a1);
        b.addAccountable("RADICE1",a2);
        b.applicaAccountable("RADICE1");
        str = c1.toString();
        System.out.println(str);
        b.operazione("RADICE1",-20);
        b.operazione("RADICE1",20);
        str = c1.toString();
        System.out.println(str);*/







    }
}
