import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        zonaDeCarga.agregarPedido(new PedidoComida(1, "Av. Siempre Viva 123"));
        zonaDeCarga.agregarPedido(new PedidoEncomienda(2, "Calle Los Aromos 456", 3.5));
        zonaDeCarga.agregarPedido(new PedidoExpress(3, "Pasaje Las Rosas 789"));
        zonaDeCarga.agregarPedido(new PedidoComida(4, "Av. Central 1010"));
        zonaDeCarga.agregarPedido(new PedidoEncomienda(5, "Calle El Bosque 202", 1.2));
        zonaDeCarga.agregarPedido(new PedidoExpress(6, "Av. Del Mar 303"));
        zonaDeCarga.agregarPedido(new PedidoComida(7, "Calle San Martín 404"));

        System.out.println("Total de pedidos en la zona de carga: "
                + zonaDeCarga.pedidosRestantes());
        System.out.println("----------------------------------------------------");

        Repartidor r1 = new Repartidor("Repartidor-1", zonaDeCarga);
        Repartidor r2 = new Repartidor("Repartidor-2", zonaDeCarga);
        Repartidor r3 = new Repartidor("Repartidor-3", zonaDeCarga);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(r1);
        executor.execute(r2);
        executor.execute(r3);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("----------------------------------------------------");
        System.out.println("Todos los pedidos han sido entregados correctamente");
    }
}
