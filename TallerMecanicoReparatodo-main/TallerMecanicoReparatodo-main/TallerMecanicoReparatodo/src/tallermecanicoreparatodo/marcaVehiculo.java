package tallermecanicoreparatodo;

public class MarcaVehiculo {

    private String marca;
    private String paisOrigen;
    private String caracteristicas;
    private boolean estado;

    public MarcaVehiculo(String marca, String paisOringen, String caracteristicas, boolean estado) {
        this.marca = marca;
        this.paisOrigen = paisOringen;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPaisOringen() {
        return paisOrigen;
    }

    public void setPaisOringen(String paisOringen) {
        this.paisOrigen = paisOringen;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean buscarCampos(String busqueda) {
        if (marca.toLowerCase().contains(busqueda)) {
            return true;
        } else if (paisOrigen.toLowerCase().contains(busqueda)) {
            return true;
        } else if (caracteristicas.toLowerCase().contains(busqueda)) {
            return true;
        } else {
            return false;
        }
    }

}
