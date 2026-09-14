public abstract class Pedido {

    private final int id;
    private final String direccionEntrega;
    private EstadoPedido estado;

    protected Pedido(int id, String direccionEntrega) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public synchronized void setEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setEstado(String nuevoEstado) {
        setEstado(EstadoPedido.valueOf(nuevoEstado.trim().toUpperCase()));
    }

    public abstract double calcularCostoEnvio();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", estado=" + estado +
                ", costoEnvio=" + calcularCostoEnvio() +
                '}';
    }
}
