public class PedidoExpress extends Pedido {

    public PedidoExpress(int id, String direccion, double distanciaKm) {
        super(id, direccion, distanciaKm);
    }

    @Override
    public String getTipo() {
        return "Express";
    }

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Camila Rojas (bici eléctrica)";
        System.out.println("Repartidor asignado automáticamente: " + repartidorAsignado);
    }

    @Override
    public double calcularTiempoEntrega() {
        return 8 + (distanciaKm * 2.5);
    }
}