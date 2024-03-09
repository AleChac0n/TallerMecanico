package tallermecanicoreparatodo;
public class OperarioCliente {
    
    private Miscelaneos m = new Miscelaneos();
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String correoElec;
    private boolean estado = true;

    public OperarioCliente(String nombre, String primerApellido, String segundoApellido, String ciudad, String direccion, String telefono, String correoElec, boolean estado) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElec = correoElec;
        this.estado = true;
    }
    
    public boolean getEstado(){
        return estado;
    }
    
    public void setEstado(boolean estado){
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElec() {
        return correoElec;
    }

    public void setCorreoElec(String correoElec) {
        this.correoElec = correoElec;
    }
    
    public boolean buscarCampos(String busqueda){
        if (nombre.toLowerCase().contains(busqueda)){
            return true;
        }
        else if (primerApellido.toLowerCase().contains(busqueda)){
            return true;
        }
        else if (segundoApellido.toLowerCase().contains(busqueda)){
            return true;
        }
        else if (ciudad.toLowerCase().contains(busqueda)){
            return true;
        }
        else if (direccion.toLowerCase().contains(busqueda)){
            return true;
        }
        else if (telefono.toLowerCase().contains(busqueda)){
            return true;
        }
        else if (correoElec.toLowerCase().contains(busqueda)){
            return true;
        }
        else {
            return false;
        }
    }
}
