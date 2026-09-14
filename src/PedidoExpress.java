public class PedidoExpress extends Pedido implements Despachable, Rastreable {

    public PedidoExpress(int id, String direccionEntrega) {
        super(id, direccionEntrega);
    }

    @Override
    public void despachar() {
        setEstado(EstadoPedido.EN_REPARTO);
        System.out.println("[PedidoExpress #" + getId() + "] despachado PRIORITARIO hacia "
                + getDireccionEntrega() + " -> estado: " + getEstado());
    }

    @Override
    public String rastrearUbicacion() {
        return "Express #" + getId() + " en estado " + getEstado()
                + ", con destino a " + getDireccionEntrega();
    }

    @Override
    public double calcularCostoEnvio() {
        return 3500.0;
    }
}
