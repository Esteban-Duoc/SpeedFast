import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpeedFastGUI ventana = new SpeedFastGUI();
            ventana.setVisible(true);
        });
    }
}
