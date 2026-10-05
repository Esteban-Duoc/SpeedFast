public class Repartidor implements Runnable {

    private int id;
    private final String nombre;
    private ZonaDeCarga zonaDeCarga;

    // Constructor usado por la aplicación de las semanas anteriores.
    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    // Constructor usado por JDBC.
    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public ZonaDeCarga getZonaDeCarga() {
        return zonaDeCarga;
    }

    public void setZonaDeCarga(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        if (zonaDeCarga == null) {
            return;
        }

        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                System.out.println(nombre + ": no quedan pedidos disponibles, finalizo turno.");
                break;
            }

            System.out.println(nombre + " tomó el pedido #" + pedido.getId());

            if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
                continue;
            }

            if (pedido instanceof Despachable) {
                ((Despachable) pedido).despachar();
            } else {
                pedido.setEstado(EstadoPedido.EN_REPARTO);
            }

            if (pedido instanceof Rastreable) {
                System.out.println("  -> " + ((Rastreable) pedido).rastrearUbicacion());
            }

            try {
                Thread.sleep(1000 + (long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nombre + ": interrumpido entregando el pedido #" + pedido.getId());
                return;
            }

            pedido.setEstado(EstadoPedido.ENTREGADO);
            System.out.println(nombre + " entregó el pedido #" + pedido.getId()
                    + " -> estado final: " + pedido.getEstado()
                    + " (costo envío: $" + pedido.calcularCostoEnvio() + ")");
        }
    }
}
