import Exceptions.InvalidOperationException;
import Exceptions.OperationErrorException;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Conto implements InterfaceConto{
    protected String cf;
    protected String iban;
    protected double saldo;
    protected ArrayList<Accountable> accountables;


    public Conto(String cf,String iban) {
        this.cf = cf;
        this.iban=iban;
        this.saldo=0;
        this.accountables=new ArrayList<Accountable>();
    }

    public String getIban(){
        return this.iban;
    }
    public double getSaldo(){
        return this.saldo;
    }
    public boolean operazione(double qta){
        try{
                checkSaldo(qta);
                saldo+=qta;
                return true;
        }catch( OperationErrorException exc){
            System.err.println(exc.getMessage() + " nel conto "+this.iban);
        }
        return false;

    }

    public boolean addAccountable(TipoAccountable type, double importo){
        switch(type){
            case ADDEBITO:
                accountables.add(new AbbonamentoSky(type,importo));
                break;
            case ACCREDITO:
                accountables.add(new Stipendio(type,importo));
                break;
        }
        return true;
    }
    public boolean applicaAccountable() {
       boolean flag= true;
       for(Accountable acc:accountables){
           if (acc.getType() == TipoAccountable.ADDEBITO) {
               if (saldo > acc.getImporto()) {
                   saldo -= acc.getImporto();
               } else {
                   flag = false; //saldo insufficiente per l'addebito
               }
           } else {
               saldo += acc.getImporto();
           }
       }
       try{
           checkFlag(flag);
       }catch(OperationErrorException exc){
           System.err.println(exc.getMessage() + " Ipossibile addebitare accountable");
       }
       return flag;

    }

    public void checkSaldo(double qta){
        if(this.saldo<(-qta)){
            throw new OperationErrorException();   //saldo insufficiente per il prelievo
        }

    }
    private void checkFlag(boolean flag){
        if(flag==false){
            throw new OperationErrorException();
        }

    }
    public String toString(){
        String str=cf+" "+iban + " " +saldo +"\n"+"LISTA ACCOUNTABLES:\n";
        for(Accountable acc: accountables) {
            str+="- Nome: " + acc.getClass().getName() + " Tipo: " + acc.getType() + " Importo: " + acc.getImporto()+"\n";
        }
        return str;
    }



}
