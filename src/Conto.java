public abstract class Conto implements InterfaceConto{
    protected String cf;
    protected String iban;
    protected double saldo;

    public Conto(String cf,String iban) {
        this.cf = cf;
        this.iban=iban;
        this.saldo=0;
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


}
