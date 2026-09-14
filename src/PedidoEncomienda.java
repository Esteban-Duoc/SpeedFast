public class PedidoEncomienda extends Pedido implements Despachable, Cancelable, Rastreable {

    private final double pesoKg;

    public PedidoEncomienda(int id, String direccionEntrega, double pesoKg) {
        super(id, direccionEntrega);
        this.pesoKg = pesoKg;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    @Override
    public void despachar() {
        setEstado(EstadoPedido.EN_REPARTO);
        System.out.println("[PedidoEncomienda #" + getId() + "] despachado hacia " + getDireccionEntrega()
                + " (" + pesoKg + " kg) -> estado: " + getEstado());
    }

    @Override
    public void cancelar() {
        setEstado(EstadoPedido.CANCELADO);
        System.out.println("[PedidoEncomienda #" + getId() + "] cancelado.");
    }

    @Override
    public String rastrearUbicacion() {
        return "Encomienda #" + getId() + " en estado " + getEstado()
                + ", con destino a " + getDireccionEntrega();
    }

    @Override
    public double calcularCostoEnvio() {
        return 1000.0 + (pesoKg * 500.0);
    }
}
