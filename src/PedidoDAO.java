import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public boolean create(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, obtenerTipo(pedido));
            ps.setString(3, pedido.getEstado().name());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Pedido> readAll() throws SQLException {
        return readAll(null, null);
    }

    public List<Pedido> readAll(String estado, String tipo) throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT id, direccion, tipo, estado FROM pedidos WHERE 1=1");

        List<String> filtros = new ArrayList<>();

        if (estado != null && !estado.equals("TODOS")) {
            sql.append(" AND estado = ?");
            filtros.add(estado);
        }

        if (tipo != null && !tipo.equals("TODOS")) {
            sql.append(" AND tipo = ?");
            filtros.add(tipo);
        }

        sql.append(" ORDER BY id");

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < filtros.size(); i++) {
                ps.setString(i + 1, filtros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(crearPedido(
                            rs.getInt("id"),
                            rs.getString("direccion"),
                            rs.getString("tipo"),
                            rs.getString("estado")
                    ));
                }
            }
        }

        return lista;
    }

    public boolean update(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedidos SET direccion = ?, tipo = ?, estado = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, obtenerTipo(pedido));
            ps.setString(3, pedido.getEstado().name());
            ps.setInt(4, pedido.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private String obtenerTipo(Pedido pedido) {
        if (pedido instanceof PedidoComida) {
            return "COMIDA";
        }
        if (pedido instanceof PedidoEncomienda) {
            return "ENCOMIENDA";
        }
        return "EXPRESS";
    }

    private Pedido crearPedido(int id, String direccion, String tipo, String estado) {
        Pedido pedido;

        switch (tipo) {
            case "COMIDA" -> pedido = new PedidoComida(id, direccion);
            case "ENCOMIENDA" -> pedido = new PedidoEncomienda(id, direccion, 0);
            case "EXPRESS" -> pedido = new PedidoExpress(id, direccion);
            default -> throw new IllegalArgumentException("Tipo de pedido desconocido: " + tipo);
        }

        pedido.setEstado(estado);
        return pedido;
    }
}
