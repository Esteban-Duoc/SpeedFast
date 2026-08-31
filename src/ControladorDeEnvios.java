import java.util.ArrayList;
import java.util.List;

public class ControladorDeEnvios implements Despachable, Cancelable, Rastreable {

    private List<String> historial = new ArrayList<>();

    @Override
    public void despachar(Pedido pedido) {
        pedido.setEstado("despachado");
        System.out.println("Pedido despachado correctamente.");
        historial.add(pedido.getTipo() + " #" + pedido.getId()
                + " – entregado por " + pedido.getRepartidorAsignado());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("Cancelando Pedido " + pedido.getTipo() + " #" + pedido.getId() + "...");
        pedido.setEstado("cancelado");
        System.out.println("→ Pedido cancelado exitosamente.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial:");
        if (historial.isEmpty()) {
            System.out.println("(sin entregas registradas todavía)");
            return;
        }
        for (String entrada : historial) {
            System.out.println("- " + entrada);
        }
    }
}