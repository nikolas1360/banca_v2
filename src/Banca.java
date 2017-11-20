import java.util.LinkedHashMap;
import java.util.Map;

public class Banca {
    private  String nome;
    private static final String radiceIban="RADICE";
    private int nConti;
    private Map<String, Conto> conti;

    public Banca(String nome){
        this.nome=nome;
        this.conti=new LinkedHashMap<String,Conto>();
        this.nConti=0;
    }

    public Conto aggiungiConto(TipoConto type, String cf){
        nConti++;
        String iban=radiceIban+nConti;
        switch (type){
            case CORRENTE:
                conti.put(iban,new ContoCorrente(cf,iban));
                return conti.get(iban);

            case DEPOSITO:
                conti.put(iban,new ContoDeposito(cf,iban));
                return conti.get(iban);

            case CORRENTEWEB:
                conti.put(iban,new ContoCorrenteWeb(cf,iban));
                return conti.get(iban);

            default:
                return null;
        }
    }

    public boolean operazione(String iban, double importo){
        if(conti.containsKey(iban)){
            Conto c = conti.get(iban);
            return c.operazione(importo);

        }else{
            return false;
        }

    }

    public boolean login(String iban, String password){
        if(conti.containsKey(iban)){
            Conto c = conti.get(iban);
            if(c instanceof ContoCorrenteWeb){
                return ((ContoCorrenteWeb) c).logIn(password);
            }else{
                return false;  //IL CONTO RELATIVO ALL'IBAN NON E' DI TIPO WEB
            }
        }else{
            return false;   //iban non presente nell'hashmap
        }
    }

    public boolean changePassword(String iban, String newPassword, String oldPassword){
        if(conti.containsKey(iban)){
            Conto c = conti.get(iban);
            if(c instanceof ContoCorrenteWeb){
               return ((ContoCorrenteWeb) c).changePassword(oldPassword,newPassword);
            }else{
                return false;  //IL CONTO RELATIVO ALL'IBAN NON E' DI TIPO WEB
            }
        }else{
            return false;   //iban non presente nell'hashmap
        }
    }

    public boolean addAccountable(String iban, TipoAccountable type, double importo){
        if(conti.containsKey(iban)){
            Conto c = conti.get(iban);
            return c.addAccountable(type, importo);

        }
        return false; //non esiste un conto con questo iban
    }

    public boolean applicaAccountable(String iban){
        if(conti.containsKey(iban)){
            Conto c = conti.get(iban);
            return c.applicaAccountable();

        }
        return false; //non esiste un conto con questo iban


    }

}
