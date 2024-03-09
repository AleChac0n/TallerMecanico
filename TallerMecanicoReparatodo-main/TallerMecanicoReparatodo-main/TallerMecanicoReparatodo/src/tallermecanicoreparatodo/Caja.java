package tallermecanicoreparatodo;

import java.time.LocalDateTime;

public class Caja {

    private Miscelaneos m = new Miscelaneos();
    private double ingresoDiario;
    private LocalDateTime fechaHora ;
    private Factura facturas[];

    public Caja(double ingresoDiario, LocalDateTime fechaHora, Factura[] facturas) {
        this.ingresoDiario = ingresoDiario;
        this.fechaHora = fechaHora;
        this.facturas = facturas;
    }

    public Miscelaneos getM() {
        return m;
    }

    public void setM(Miscelaneos m) {
        this.m = m;
    }

    public double getIngresoDiario() {
        return ingresoDiario;
    }

    public void setIngresoDiario(double ingresoDiario) {
        this.ingresoDiario = ingresoDiario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Factura[] getFacturas() {
        return facturas;
    }

    public void setFacturas(Factura[] facturas) {
        this.facturas = facturas;
    }
    
    public boolean buscarCampos(String busqueda){
        String fecHora = fechaHora.getDayOfMonth()+"/"+fechaHora.getMonthValue()+"/"+fechaHora.getYear();
        if (fecHora.toLowerCase().contains(busqueda)){
            return true;
        } else {
            return false;
        }
    }
    
}
