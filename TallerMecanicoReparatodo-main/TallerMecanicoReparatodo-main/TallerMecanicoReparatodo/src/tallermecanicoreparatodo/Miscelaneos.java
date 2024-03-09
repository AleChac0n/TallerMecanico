package tallermecanicoreparatodo;

import java.awt.TextArea;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Miscelaneos {

    public int recibeInt(String mensaje) {
        String msj = null;
        int res = 0;
        while (msj == null) {
            try {
                msj = JOptionPane.showInputDialog(mensaje);
                res = Integer.parseInt(msj);
            } catch (NumberFormatException error) {
                imprimeMensaje("Debe ingresar un número entero");
                msj = null;
            }
        }

        return res;
    }

    public double recibeDouble(String mensaje) {
        String msj = null;
        double res = 0;
        while (msj == null) {
            try {
                msj = JOptionPane.showInputDialog(mensaje);
                res = Double.parseDouble(msj);
            } catch (NumberFormatException error) {
                imprimeMensaje("Debe ingresar un número entero");
                msj = null;
            }
        }
        return res;
    }

    public String recibeString(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    public char recibeChar(String mensaje) {
        return JOptionPane.showInputDialog(mensaje).toUpperCase().charAt(0);
    }

    public void imprimeMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void imprimeMensajeTextArea(String mensaje) {
        TextArea msj = new TextArea(mensaje);
        JOptionPane.showMessageDialog(null, msj);
    }

    public int mostrarTabla(String[] titulos, String[][] contenido) {
        JTable tabla = new JTable(contenido, titulos);
        JOptionPane.showMessageDialog(null, new JScrollPane(tabla));
        //Si se selecciona una fila de la tabla mostrada devuelve la posición.
        //de lo contrario devuelve -1
        return tabla.getSelectedRow();
    }
}
