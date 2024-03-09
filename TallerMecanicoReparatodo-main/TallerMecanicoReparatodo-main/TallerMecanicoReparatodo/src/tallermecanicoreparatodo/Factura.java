package tallermecanicoreparatodo;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
public class Factura {
    private Usuario usuario;
    private OperarioCliente cliente;
    private OperarioCliente operario;
    private Vehiculo vehiculo;
    private Reparacion reparaciones[];
    private LocalDateTime fechaHora ;
    private float costoTotal;
    private int contRep=0;

    public Factura(int numRepaciones){
        this.reparaciones = new Reparacion[numRepaciones];
        fechaHora = LocalDateTime.now();
        this.costoTotal = 0;
    }

    public void calcularCostoTotal(){
        this.costoTotal = 0;
        for(int i = 0; i < reparaciones.length; i++){
            this.costoTotal += reparaciones[i].getPrecio();
        }
        //TODO sumar impuestos si es necesario
    }
    
    public void agregarRep(){
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public OperarioCliente getCliente() {
        return cliente;
    }

    public void setCliente(OperarioCliente cliente) {
        this.cliente = cliente;
    }

    public OperarioCliente getOperario() {
        return operario;
    }

    public void setOperario(OperarioCliente operario) {
        this.operario = operario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Reparacion[] getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(Reparacion[] reparaciones) {
        this.reparaciones = reparaciones;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public int getContRep() {
        return contRep;
    }

    public void setContRep(int contRep) {
        this.contRep = contRep;
    }
     
    public boolean buscarCampos(String busqueda){
        if (cliente.getNombre().toLowerCase().contains(busqueda)){
            return true;
        }
        else if (operario.getNombre().toLowerCase().contains(busqueda)){
            return true;
        }
        else if (usuario.getNombre().toLowerCase().contains(busqueda)){
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean buscarFecha(String busqueda){
        String fecHora = fechaHora.getDayOfMonth()+"/"+fechaHora.getMonthValue()+"/"+fechaHora.getYear();
        if (fecHora.toLowerCase().contains(busqueda)){
            return true;
        } else {
            return false;
        }
    }
    
    public String getFecha(){
        return fechaHora.getDayOfMonth()+"/"+fechaHora.getMonthValue()+"/"+fechaHora.getYear();
    }

}
