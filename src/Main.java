public class Main {
    public static void main(String[] args) {

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        Pedido pedido101 = new PedidoComida(101, "Av. Libertad 123", 4);
        pedido101.asignarRepartidor();
        pedido101.mostrarResumen();
        controlador.despachar(pedido101);

        System.out.println();

        Pedido pedido102 = new PedidoEncomienda(102, "Av. Santa Rosa 567", 7);
        pedido102.asignarRepartidor("Daniela Tapia");
        pedido102.mostrarResumen();
        controlador.despachar(pedido102);

        System.out.println();

        Pedido pedido103 = new PedidoExpress(103, "Calle Los Aromos 89", 2);
        pedido103.asignarRepartidor();
        pedido103.mostrarResumen();
        controlador.cancelar(pedido103);

        System.out.println();

        controlador.verHistorial();
    }
}