package tallermecanicoreparatodo;
import javax.swing.JOptionPane ;
public class Reparacion {
    private String dano;
    private String areaAfectada;
    private double precio;
    private boolean estado = true;

    public Reparacion(String dano, String areaAfectada, double precio, boolean estado) {
        this.dano = dano;
        this.areaAfectada = areaAfectada;
        this.precio = precio;
        this.estado = true;
    }

    public String getDano() {
        return dano;
    }

    public void setDano(String dano) {
        this.dano = dano;
    }

    public String getAreaAfectada() {
        return areaAfectada;
    }

    public void setAreaAfectada(String areaAfectada) {
        this.areaAfectada = areaAfectada;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public boolean buscarCampos(String busqueda){
        if (dano.toLowerCase().contains(busqueda)){
            return true;
        }
        else if (areaAfectada.toLowerCase().contains(busqueda)){
            return true;
        }
        else {
            return false;
        }
    }
}
    
