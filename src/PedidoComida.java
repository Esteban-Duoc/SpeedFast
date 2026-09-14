public class PedidoComida extends Pedido implements Despachable, Cancelable {

    public PedidoComida(int id, String direccionEntrega) {
        super(id, direccionEntrega);
    }

    @Override
    public void despachar() {
        setEstado(EstadoPedido.EN_REPARTO);
        System.out.println("[PedidoComida #" + getId() + "] despachado hacia " + getDireccionEntrega()
                + " -> estado: " + getEstado());
    }

    @Override
    public void cancelar() {
        setEstado(EstadoPedido.CANCELADO);
        System.out.println("[PedidoComida #" + getId() + "] cancelado.");
    }

    @Override
    public double calcularCostoEnvio() {
        return 1500.0;
    }
}
