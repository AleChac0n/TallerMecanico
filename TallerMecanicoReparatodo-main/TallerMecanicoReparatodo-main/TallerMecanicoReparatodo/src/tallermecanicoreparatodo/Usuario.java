package tallermecanicoreparatodo;
public class Usuario {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String usuario;
    private char tipo;
    private String password;
    private boolean estado=true;

    public Usuario(String nombre, String primerApellido, String segundoApellido, String usuario, char tipo, String password, boolean estado) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.usuario = usuario;
        this.tipo = tipo;
        this.password = password;
        this.estado = true;
    }

    public Usuario() {
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
        else if (usuario.toLowerCase().contains(busqueda)){
            return true;
        } else {
            return false;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
