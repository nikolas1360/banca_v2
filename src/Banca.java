import Exceptions.InvalidIbanException;
import Exceptions.NotWebException;

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
        try{
            Conto c= getConto(iban);
            return c.operazione(importo);
        }catch (InvalidIbanException exc){
            System.err.println(iban + ": "+exc.getMessage());
        }
        return false;
    }

    public boolean login(String iban, String password){
        try{
            Conto c = getConto(iban);
            isWeb(c);
            return ((ContoCorrenteWeb) c).logIn(password);

        }catch(InvalidIbanException exc){
            System.err.println(iban + ": " + exc.getMessage());
        }
        catch(NotWebException e){
        System.err.println(e.getMessage());
        }
        return false;


    }

    public boolean changePassword(String iban, String newPassword, String oldPassword){
        try{
            Conto c = getConto(iban);
            isWeb(c);
            return ((ContoCorrenteWeb) c).changePassword(oldPassword,newPassword);

        }catch(InvalidIbanException exc){
            System.err.println(iban + ": " + exc.getMessage());
        }
        catch(NotWebException e){
            System.err.println(e.getMessage());

        }
        return false;
    }

    public boolean addAccountable(String iban, Accountable acc){
        try{
            Conto c=getConto(iban);
            return c.addAccountable(acc.getType(), acc.getImporto());

        }catch(InvalidIbanException exc){
            System.err.println(iban + ": " + exc.getMessage());

        }
        return false;

    }

    public boolean applicaAccountable(String iban){
        try{
            Conto c=getConto(iban);
            return c.applicaAccountable();

        }catch(InvalidIbanException exc){
            System.err.println(iban + ": " + exc.getMessage());

        }
        return false;
    }

    private void isWeb(Conto c){
        if(!(c instanceof ContoCorrenteWeb)){
            throw new NotWebException();
        }



    }
    private Conto getConto(String iban){
        if(conti.containsKey(iban)){
            Conto c = conti.get(iban);
            return c;

        }else
        {
            throw new InvalidIbanException();
        }


    }
}
