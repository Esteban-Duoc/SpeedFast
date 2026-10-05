import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SpeedFastGUI extends JFrame {

    private final RepartidorDAO repartidorDAO = new RepartidorDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final EntregaDAO entregaDAO = new EntregaDAO();

    private final DefaultTableModel modeloRepartidores = modelo("ID", "Nombre");
    private final DefaultTableModel modeloPedidos = modelo("ID", "Dirección", "Tipo", "Estado");
    private final DefaultTableModel modeloEntregas = modelo("ID", "Pedido", "Repartidor", "Fecha", "Hora");

    private JTable tablaRepartidores;
    private JTable tablaPedidos;
    private JTable tablaEntregas;

    private JTextField txtNombreRepartidor;
    private JTextField txtDireccionPedido;
    private JComboBox<String> cmbTipoPedido;
    private JComboBox<String> cmbEstadoPedido;
    private JComboBox<String> cmbFiltroTipo;
    private JComboBox<String> cmbFiltroEstado;

    private JComboBox<ItemCombo> cmbPedidoEntrega;
    private JComboBox<ItemCombo> cmbRepartidorEntrega;
    private JTextField txtFechaEntrega;
    private JTextField txtHoraEntrega;

    public SpeedFastGUI() {
        setTitle("SpeedFast - Gestión de pedidos y entregas");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Repartidores", panelRepartidores());
        pestañas.addTab("Pedidos", panelPedidos());
        pestañas.addTab("Entregas", panelEntregas());

        add(pestañas);

        cargarRepartidores();
        cargarPedidos();
        cargarCombosEntrega();
        cargarEntregas();
    }

    private JPanel panelRepartidores() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formulario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtNombreRepartidor = new JTextField(20);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnEditar = new JButton("Editar seleccionado");
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        JButton btnActualizar = new JButton("Actualizar");

        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombreRepartidor);
        formulario.add(btnRegistrar);
        formulario.add(btnEditar);
        formulario.add(btnEliminar);
        formulario.add(btnActualizar);

        tablaRepartidores = new JTable(modeloRepartidores);

        btnRegistrar.addActionListener(e -> registrarRepartidor());
        btnEditar.addActionListener(e -> editarRepartidor());
        btnEliminar.addActionListener(e -> eliminarRepartidor());
        btnActualizar.addActionListener(e -> cargarRepartidores());

        tablaRepartidores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaRepartidores.getSelectedRow() >= 0) {
                txtNombreRepartidor.setText(
                        String.valueOf(modeloRepartidores.getValueAt(
                                tablaRepartidores.getSelectedRow(), 1)));
            }
        });

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaRepartidores), BorderLayout.CENTER);
        return panel;
    }

    private JPanel panelPedidos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formulario = new JPanel(new GridLayout(3, 1));

        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtDireccionPedido = new JTextField(25);
        cmbTipoPedido = new JComboBox<>(new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"});
        cmbEstadoPedido = new JComboBox<>(new String[]{"PENDIENTE", "EN_REPARTO", "ENTREGADO"});

        fila1.add(new JLabel("Dirección:"));
        fila1.add(txtDireccionPedido);
        fila1.add(new JLabel("Tipo:"));
        fila1.add(cmbTipoPedido);
        fila1.add(new JLabel("Estado:"));
        fila1.add(cmbEstadoPedido);

        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnEditar = new JButton("Editar seleccionado");
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        JButton btnActualizar = new JButton("Actualizar");

        fila2.add(btnRegistrar);
        fila2.add(btnEditar);
        fila2.add(btnEliminar);
        fila2.add(btnActualizar);

        JPanel fila3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cmbFiltroEstado = new JComboBox<>(new String[]{"TODOS", "PENDIENTE", "EN_REPARTO", "ENTREGADO"});
        cmbFiltroTipo = new JComboBox<>(new String[]{"TODOS", "COMIDA", "ENCOMIENDA", "EXPRESS"});
        JButton btnFiltrar = new JButton("Aplicar filtros");

        fila3.add(new JLabel("Filtrar estado:"));
        fila3.add(cmbFiltroEstado);
        fila3.add(new JLabel("Filtrar tipo:"));
        fila3.add(cmbFiltroTipo);
        fila3.add(btnFiltrar);

        formulario.add(fila1);
        formulario.add(fila2);
        formulario.add(fila3);

        tablaPedidos = new JTable(modeloPedidos);

        btnRegistrar.addActionListener(e -> registrarPedido());
        btnEditar.addActionListener(e -> editarPedido());
        btnEliminar.addActionListener(e -> eliminarPedido());
        btnActualizar.addActionListener(e -> cargarPedidos());
        btnFiltrar.addActionListener(e -> cargarPedidos());

        tablaPedidos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaPedidos.getSelectedRow() >= 0) {
                int fila = tablaPedidos.getSelectedRow();
                txtDireccionPedido.setText(String.valueOf(modeloPedidos.getValueAt(fila, 1)));
                cmbTipoPedido.setSelectedItem(modeloPedidos.getValueAt(fila, 2));
                cmbEstadoPedido.setSelectedItem(modeloPedidos.getValueAt(fila, 3));
            }
        });

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);
        return panel;
    }

    private JPanel panelEntregas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formulario = new JPanel(new GridLayout(3, 1));

        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cmbPedidoEntrega = new JComboBox<>();
        cmbRepartidorEntrega = new JComboBox<>();
        fila1.add(new JLabel("Pedido:"));
        fila1.add(cmbPedidoEntrega);
        fila1.add(new JLabel("Repartidor:"));
        fila1.add(cmbRepartidorEntrega);

        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtFechaEntrega = new JTextField(LocalDate.now().toString(), 10);
        txtHoraEntrega = new JTextField(LocalTime.now().withNano(0).toString(), 8);
        fila2.add(new JLabel("Fecha (AAAA-MM-DD):"));
        fila2.add(txtFechaEntrega);
        fila2.add(new JLabel("Hora (HH:MM:SS):"));
        fila2.add(txtHoraEntrega);

        JPanel fila3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnEditar = new JButton("Editar seleccionado");
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        JButton btnActualizar = new JButton("Actualizar combos y tabla");

        fila3.add(btnRegistrar);
        fila3.add(btnEditar);
        fila3.add(btnEliminar);
        fila3.add(btnActualizar);

        formulario.add(fila1);
        formulario.add(fila2);
        formulario.add(fila3);

        tablaEntregas = new JTable(modeloEntregas);

        btnRegistrar.addActionListener(e -> registrarEntrega());
        btnEditar.addActionListener(e -> editarEntrega());
        btnEliminar.addActionListener(e -> eliminarEntrega());
        btnActualizar.addActionListener(e -> {
            cargarCombosEntrega();
            cargarEntregas();
        });

        tablaEntregas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaEntregas.getSelectedRow() >= 0) {
                int fila = tablaEntregas.getSelectedRow();
                seleccionarComboPorId(cmbPedidoEntrega,
                        Integer.parseInt(String.valueOf(modeloEntregas.getValueAt(fila, 1))));
                seleccionarComboPorId(cmbRepartidorEntrega,
                        Integer.parseInt(String.valueOf(modeloEntregas.getValueAt(fila, 2))));
                txtFechaEntrega.setText(String.valueOf(modeloEntregas.getValueAt(fila, 3)));
                txtHoraEntrega.setText(String.valueOf(modeloEntregas.getValueAt(fila, 4)));
            }
        });

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaEntregas), BorderLayout.CENTER);
        return panel;
    }

    private void registrarRepartidor() {
        String nombre = txtNombreRepartidor.getText().trim();

        if (nombre.isEmpty()) {
            mostrarError("El nombre del repartidor es obligatorio.");
            return;
        }

        try {
            repartidorDAO.create(new Repartidor(nombre));
            mostrarExito("Repartidor registrado correctamente.");
            txtNombreRepartidor.setText("");
            cargarRepartidores();
            cargarCombosEntrega();
        } catch (SQLException ex) {
            mostrarError("No se pudo registrar el repartidor.\n" + ex.getMessage());
        }
    }

    private void editarRepartidor() {
        int fila = tablaRepartidores.getSelectedRow();
        if (fila < 0) {
            mostrarError("Selecciona un repartidor.");
            return;
        }

        String nombre = txtNombreRepartidor.getText().trim();
        if (nombre.isEmpty()) {
            mostrarError("El nombre es obligatorio.");
            return;
        }

        int id = Integer.parseInt(String.valueOf(modeloRepartidores.getValueAt(fila, 0)));

        try {
            repartidorDAO.update(new Repartidor(id, nombre));
            mostrarExito("Repartidor actualizado correctamente.");
            cargarRepartidores();
            cargarCombosEntrega();
        } catch (SQLException ex) {
            mostrarError("No se pudo actualizar el repartidor.\n" + ex.getMessage());
        }
    }

    private void eliminarRepartidor() {
        int fila = tablaRepartidores.getSelectedRow();
        if (fila < 0) {
            mostrarError("Selecciona un repartidor.");
            return;
        }

        int id = Integer.parseInt(String.valueOf(modeloRepartidores.getValueAt(fila, 0)));

        if (!confirmar("¿Eliminar el repartidor seleccionado?")) {
            return;
        }

        try {
            repartidorDAO.delete(id);
            mostrarExito("Repartidor eliminado correctamente.");
            cargarRepartidores();
            cargarCombosEntrega();
        } catch (SQLException ex) {
            mostrarError("No se pudo eliminar. Puede existir una entrega asociada.\n" + ex.getMessage());
        }
    }

    private void registrarPedido() {
        String direccion = txtDireccionPedido.getText().trim();

        if (direccion.isEmpty()) {
            mostrarError("La dirección es obligatoria.");
            return;
        }

        try {
            Pedido pedido = crearPedidoDesdeFormulario(0, direccion);
            pedidoDAO.create(pedido);
            mostrarExito("Pedido registrado correctamente.");
            txtDireccionPedido.setText("");
            cargarPedidos();
            cargarCombosEntrega();
        } catch (SQLException ex) {
            mostrarError("No se pudo registrar el pedido.\n" + ex.getMessage());
        }
    }

    private void editarPedido() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila < 0) {
            mostrarError("Selecciona un pedido.");
            return;
        }

        String direccion = txtDireccionPedido.getText().trim();
        if (direccion.isEmpty()) {
            mostrarError("La dirección es obligatoria.");
            return;
        }

        int id = Integer.parseInt(String.valueOf(modeloPedidos.getValueAt(fila, 0)));

        try {
            Pedido pedido = crearPedidoDesdeFormulario(id, direccion);
            pedidoDAO.update(pedido);
            mostrarExito("Pedido actualizado correctamente.");
            cargarPedidos();
            cargarCombosEntrega();
        } catch (SQLException ex) {
            mostrarError("No se pudo actualizar el pedido.\n" + ex.getMessage());
        }
    }

    private void eliminarPedido() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila < 0) {
            mostrarError("Selecciona un pedido.");
            return;
        }

        int id = Integer.parseInt(String.valueOf(modeloPedidos.getValueAt(fila, 0)));

        if (!confirmar("¿Eliminar el pedido seleccionado?")) {
            return;
        }

        try {
            pedidoDAO.delete(id);
            mostrarExito("Pedido eliminado correctamente.");
            cargarPedidos();
            cargarCombosEntrega();
        } catch (SQLException ex) {
            mostrarError("No se pudo eliminar. Puede existir una entrega asociada.\n" + ex.getMessage());
        }
    }

    private Pedido crearPedidoDesdeFormulario(int id, String direccion) {
        String tipo = String.valueOf(cmbTipoPedido.getSelectedItem());
        String estado = String.valueOf(cmbEstadoPedido.getSelectedItem());

        Pedido pedido;

        switch (tipo) {
            case "COMIDA" -> pedido = new PedidoComida(id, direccion);
            case "ENCOMIENDA" -> pedido = new PedidoEncomienda(id, direccion, 0);
            default -> pedido = new PedidoExpress(id, direccion);
        }

        pedido.setEstado(estado);
        return pedido;
    }

    private void registrarEntrega() {
        try {
            validarFormularioEntrega();

            ItemCombo pedido = (ItemCombo) cmbPedidoEntrega.getSelectedItem();
            ItemCombo repartidor = (ItemCombo) cmbRepartidorEntrega.getSelectedItem();

            Entrega entrega = new Entrega(
                    pedido.id,
                    repartidor.id,
                    LocalDate.parse(txtFechaEntrega.getText().trim()),
                    LocalTime.parse(txtHoraEntrega.getText().trim())
            );

            entregaDAO.create(entrega);
            mostrarExito("Entrega registrada correctamente.");
            cargarEntregas();
        } catch (IllegalArgumentException ex) {
            mostrarError("Fecha u hora inválida. Usa AAAA-MM-DD y HH:MM:SS.");
        } catch (SQLException ex) {
            mostrarError("No se pudo registrar la entrega.\n" + ex.getMessage());
        }
    }

    private void editarEntrega() {
        int fila = tablaEntregas.getSelectedRow();
        if (fila < 0) {
            mostrarError("Selecciona una entrega.");
            return;
        }

        try {
            validarFormularioEntrega();

            int id = Integer.parseInt(String.valueOf(modeloEntregas.getValueAt(fila, 0)));
            ItemCombo pedido = (ItemCombo) cmbPedidoEntrega.getSelectedItem();
            ItemCombo repartidor = (ItemCombo) cmbRepartidorEntrega.getSelectedItem();

            Entrega entrega = new Entrega(
                    id,
                    pedido.id,
                    repartidor.id,
                    LocalDate.parse(txtFechaEntrega.getText().trim()),
                    LocalTime.parse(txtHoraEntrega.getText().trim())
            );

            entregaDAO.update(entrega);
            mostrarExito("Entrega actualizada correctamente.");
            cargarEntregas();
        } catch (IllegalArgumentException ex) {
            mostrarError("Fecha u hora inválida. Usa AAAA-MM-DD y HH:MM:SS.");
        } catch (SQLException ex) {
            mostrarError("No se pudo actualizar la entrega.\n" + ex.getMessage());
        }
    }

    private void eliminarEntrega() {
        int fila = tablaEntregas.getSelectedRow();
        if (fila < 0) {
            mostrarError("Selecciona una entrega.");
            return;
        }

        int id = Integer.parseInt(String.valueOf(modeloEntregas.getValueAt(fila, 0)));

        if (!confirmar("¿Eliminar la entrega seleccionada?")) {
            return;
        }

        try {
            entregaDAO.delete(id);
            mostrarExito("Entrega eliminada correctamente.");
            cargarEntregas();
        } catch (SQLException ex) {
            mostrarError("No se pudo eliminar la entrega.\n" + ex.getMessage());
        }
    }

    private void validarFormularioEntrega() {
        if (cmbPedidoEntrega.getSelectedItem() == null) {
            throw new IllegalArgumentException("Debes seleccionar un pedido.");
        }

        if (cmbRepartidorEntrega.getSelectedItem() == null) {
            throw new IllegalArgumentException("Debes seleccionar un repartidor.");
        }

        LocalDate.parse(txtFechaEntrega.getText().trim());
        LocalTime.parse(txtHoraEntrega.getText().trim());
    }

    private void cargarRepartidores() {
        if (modeloRepartidores == null) return;

        modeloRepartidores.setRowCount(0);

        try {
            for (Repartidor r : repartidorDAO.readAll()) {
                modeloRepartidores.addRow(new Object[]{r.getId(), r.getNombre()});
            }
        } catch (SQLException ex) {
            mostrarError("No se pudieron cargar los repartidores.\n" + ex.getMessage());
        }
    }

    private void cargarPedidos() {
        if (modeloPedidos == null) return;

        modeloPedidos.setRowCount(0);

        try {
            String estado = cmbFiltroEstado == null ? "TODOS" :
                    String.valueOf(cmbFiltroEstado.getSelectedItem());
            String tipo = cmbFiltroTipo == null ? "TODOS" :
                    String.valueOf(cmbFiltroTipo.getSelectedItem());

            List<Pedido> pedidos = pedidoDAO.readAll(estado, tipo);

            for (Pedido p : pedidos) {
                modeloPedidos.addRow(new Object[]{
                        p.getId(),
                        p.getDireccionEntrega(),
                        obtenerTipo(p),
                        p.getEstado()
                });
            }
        } catch (SQLException ex) {
            mostrarError("No se pudieron cargar los pedidos.\n" + ex.getMessage());
        }
    }

    private void cargarCombosEntrega() {
        if (cmbPedidoEntrega == null || cmbRepartidorEntrega == null) return;

        cmbPedidoEntrega.removeAllItems();
        cmbRepartidorEntrega.removeAllItems();

        try {
            for (Pedido p : pedidoDAO.readAll()) {
                cmbPedidoEntrega.addItem(
                        new ItemCombo(p.getId(),
                                p.getId() + " - " + p.getDireccionEntrega()));
            }

            for (Repartidor r : repartidorDAO.readAll()) {
                cmbRepartidorEntrega.addItem(
                        new ItemCombo(r.getId(),
                                r.getId() + " - " + r.getNombre()));
            }
        } catch (SQLException ex) {
            mostrarError("No se pudieron cargar los combos.\n" + ex.getMessage());
        }
    }

    private void cargarEntregas() {
        if (modeloEntregas == null) return;

        modeloEntregas.setRowCount(0);

        try {
            for (Entrega e : entregaDAO.readAll()) {
                modeloEntregas.addRow(new Object[]{
                        e.getId(),
                        e.getIdPedido(),
                        e.getIdRepartidor(),
                        e.getFecha(),
                        e.getHoraTexto()
                });
            }
        } catch (SQLException ex) {
            mostrarError("No se pudieron cargar las entregas.\n" + ex.getMessage());
        }
    }

    private String obtenerTipo(Pedido pedido) {
        if (pedido instanceof PedidoComida) return "COMIDA";
        if (pedido instanceof PedidoEncomienda) return "ENCOMIENDA";
        return "EXPRESS";
    }

    private void seleccionarComboPorId(JComboBox<ItemCombo> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).id == id) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    private static DefaultTableModel modelo(String... columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(
                this, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "SpeedFast",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private static class ItemCombo {
        private final int id;
        private final String texto;

        private ItemCombo(int id, String texto) {
            this.id = id;
            this.texto = texto;
        }

        @Override
        public String toString() {
            return texto;
        }
    }
}
