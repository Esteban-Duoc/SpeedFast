public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int id, String direccion, double distanciaKm) {
        super(id, direccion, distanciaKm);
    }

    @Override
    public String getTipo() {
        return "Encomienda";
    }

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Daniela Tapia (furgón)";
        System.out.println("Repartidor asignado automáticamente: " + repartidorAsignado);
    }

    @Override
    public double calcularTiempoEntrega() {
        return 15 + (distanciaKm * 2);
    }
}