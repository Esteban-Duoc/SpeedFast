import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {

    private final Queue<Pedido> pedidosPendientes = new LinkedList<>();

    public synchronized void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
        System.out.println("[ZonaDeCarga] Se agregó " + p);
    }

    public synchronized Pedido retirarPedido() {
        return pedidosPendientes.poll();
    }

    public synchronized int pedidosRestantes() {
        return pedidosPendientes.size();
    }
}
