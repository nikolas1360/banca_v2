public class ContoDeposito extends Conto {

    public ContoDeposito(String cf, String iban) {
        super(cf,iban);

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

