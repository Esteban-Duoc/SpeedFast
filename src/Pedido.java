public abstract class Pedido {

    protected int id;
    protected String direccion;
    protected double distanciaKm;
    protected String repartidorAsignado;
    protected String estado;

    public Pedido(int id, String direccion, double distanciaKm) {
        this.id = id;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;
        this.repartidorAsignado = "Sin asignar";
        this.estado = "pendiente";
    }

    public void asignarRepartidor(String nombre) {
        this.repartidorAsignado = nombre;
        System.out.println("Repartidor asignado manualmente: " + nombre);
    }

    public void asignarRepartidor() {
        this.repartidorAsignado = "Repartidor genérico";
        System.out.println("Repartidor asignado automáticamente: " + repartidorAsignado);
    }

    public abstract double calcularTiempoEntrega();

    public abstract String getTipo();

    public void mostrarResumen() {
        System.out.println("[Pedido " + getTipo() + "]");
        System.out.println("Pedido #" + id);
        System.out.println("Dirección: " + direccion);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor asignado: " + repartidorAsignado);
        System.out.printf("Tiempo estimado: %.0f minutos%n", calcularTiempoEntrega());
    }

    public int getId() { return id; }
    public String getDireccion() { return direccion; }
    public double getDistanciaKm() { return distanciaKm; }
    public String getRepartidorAsignado() { return repartidorAsignado; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}