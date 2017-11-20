public interface InterfaceConto {

    boolean addAccountable(TipoAccountable type, double importo);
    boolean applicaAccountable();
    boolean operazione(double qta);
    String getIban();
    double getSaldo();

}
