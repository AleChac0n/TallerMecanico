package tallermecanicoreparatodo;
import javax.swing.JOptionPane;
public class Vehiculo {
    private MarcaVehiculo marcaVehiculos;
    private String estilo;
    private boolean estado=true;

    public Vehiculo(MarcaVehiculo marcaVehiculos, String estilo, boolean estado) {
        this.marcaVehiculos = marcaVehiculos;
        this.estilo = estilo;
        this.estado = true;
    }

    public MarcaVehiculo getMarcaVehiculos() {
        return marcaVehiculos;
    }

    public void setMarcaVehiculos(MarcaVehiculo marcaVehiculos) {
        this.marcaVehiculos = marcaVehiculos;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
    
    public boolean buscarCampos(String busqueda) {
        if (marcaVehiculos.getMarca().toLowerCase().contains(busqueda)) {
            return true;
        } else if (estilo.toLowerCase().contains(busqueda)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
