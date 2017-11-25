import Exceptions.InvalidOperationException;

public class ContoDeposito extends Conto {

    public ContoDeposito(String cf, String iban) {
        super(cf,iban);

    }
    @Override
    public boolean addAccountable(TipoAccountable type, double importo){

        try{
            checkTipo(type);
            accountables.add(new Stipendio(type,importo));
            return true;


        }catch(InvalidOperationException exc){
            System.err.println(exc.getMessage() + ". Impossibile applicare accountable di tipo ADDEBITO.");
        }
        return false;
    }

    @Override
    public boolean operazione(double qta){
        try{
            checkImporto(qta);
            saldo += qta;
        }catch(InvalidOperationException exc){
            System.err.println(exc.getMessage() + ". Impossibile prelevare denaro.");
        }
       return true;
    }

    private void checkTipo(TipoAccountable type){
        if(type==TipoAccountable.ADDEBITO){
            throw new InvalidOperationException();
        }


    }
   private void checkImporto(double qta){
        if(qta<0){
            throw new InvalidOperationException();
        }

    }

}



