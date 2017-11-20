public class ContoDeposito extends Conto {

    public ContoDeposito(String cf, String iban) {
        super(cf,iban);

    }
    @Override
    public boolean addAccountable(TipoAccountable type, double importo){

        if(type==TipoAccountable.ACCREDITO){
            accountables.add(new Stipendio(type,importo));
            return true;
        }
        return false; //addebiti non disponibili su questo tipo di conto
    }

    @Override
    public boolean operazione(double qta){
        if(qta>0){
            saldo+=qta;
            return true;
        }else{
            return false;   //impossibile prelevare soldi
        }
    }
}


