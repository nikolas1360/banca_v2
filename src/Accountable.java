public class Accountable implements InterfaceAccountable{
    private TipoAccountable type;
    private double importo;

    public Accountable(TipoAccountable type, double importo){
        this.type=type;
        this.importo=importo;
    }

    public TipoAccountable getType() {
        return type;
    }


    public double getImporto(){
        return this.importo;
    }
}
