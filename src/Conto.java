import java.util.ArrayList;
import java.util.Iterator;

public abstract class Conto implements InterfaceConto{
    protected String cf;
    protected String iban;
    protected double saldo;
    protected ArrayList<Accountable> accountables;
    private Iterator<Accountable> iterator;

    public Conto(String cf,String iban) {
        this.cf = cf;
        this.iban=iban;
        this.saldo=0;
        this.accountables=new ArrayList<Accountable>();
        this.iterator=accountables.iterator();
    }

    public String getIban(){
        return this.iban;
    }
    public double getSaldo(){
        return this.saldo;
    }
    public boolean operazione(double qta){
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
        return flag;
    }

    public String toString(){
        String str=cf+" "+iban + " " +saldo +"\n"+"LISTA ACCOUNTABLES:\n";
        for(Accountable acc: accountables) {
            str+="- Tipo: " + acc.getType() + " Importo: " + acc.getImporto()+"\n";
        }
        return str;
    }



}
