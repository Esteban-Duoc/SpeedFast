import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public boolean create(Entrega entrega) throws SQLException {
        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, Date.valueOf(entrega.getFecha()));
            ps.setTime(4, Time.valueOf(entrega.getHora()));
            return ps.executeUpdate() > 0;
        }
    }

    public List<Entrega> readAll() throws SQLException {
        return readAll(null, null);
    }

    public List<Entrega> readAll(Integer idPedido, Integer idRepartidor) throws SQLException {
        List<Entrega> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT id, id_pedido, id_repartidor, fecha, hora FROM entregas WHERE 1=1");
        List<Integer> filtros = new ArrayList<>();

        if (idPedido != null) {
            sql.append(" AND id_pedido = ?");
            filtros.add(idPedido);
        }

        if (idRepartidor != null) {
            sql.append(" AND id_repartidor = ?");
            filtros.add(idRepartidor);
        }

        sql.append(" ORDER BY id");

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < filtros.size(); i++) {
                ps.setInt(i + 1, filtros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Entrega(
                            rs.getInt("id"),
                            rs.getInt("id_pedido"),
                            rs.getInt("id_repartidor"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getTime("hora").toLocalTime()
                    ));
                }
            }
        }

        return lista;
    }

    public boolean update(Entrega entrega) throws SQLException {
        String sql = "UPDATE entregas SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, Date.valueOf(entrega.getFecha()));
            ps.setTime(4, Time.valueOf(entrega.getHora()));
            ps.setInt(5, entrega.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM entregas WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
