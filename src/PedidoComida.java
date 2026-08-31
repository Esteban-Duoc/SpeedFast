public class PedidoComida extends Pedido {

    public PedidoComida(int id, String direccion, double distanciaKm) {
        super(id, direccion, distanciaKm);
    }

    @Override
    public String getTipo() {
        return "Comida";
    }

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Luis Díaz (moto)";
        System.out.println("Repartidor asignado automáticamente: " + repartidorAsignado);
    }

    @Override
    public double calcularTiempoEntrega() {
        return 10 + (distanciaKm * 3);
    }
}