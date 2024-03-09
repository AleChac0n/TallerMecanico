package tallermecanicoreparatodo;

import java.time.LocalDateTime;

public class AdministradorCaja {

    Miscelaneos m = new Miscelaneos();
    private Caja ingresosDiarios[];
    private int contIngresosDiarios = 0;

    public void inicializarArreglo() {
        if (ingresosDiarios == null) {
            int tamanoIngresos = m.recibeInt("Digite la cantidad de cajas para el vector");
            ingresosDiarios = new Caja[tamanoIngresos];
        }
    }

    public void mostrarIngreso(Factura[] facturas, double ingreso) {
        //método para mostrar los ingresos totales
        String facturasImpre = "Facturas a sumar\n";
        facturasImpre += "*********************************************\n\n";
        facturasImpre += String.format("%10s\t%10s\t%15s\t%18s\n", "Usuario", "Cliente", "Fecha", "Monto");
        for (int i = 0; i < facturas.length; i++) {
            String fecHora = facturas[i].getFecha();
            facturasImpre += String.format("%10s\t%10s\t%15s\t%15.2f\n", facturas[i].getUsuario().getNombre(), facturas[i].getCliente().getNombre(), fecHora, facturas[i].getCostoTotal(),"\n");
        }
        facturasImpre += "Total del día: "+ingreso;
        m.imprimeMensajeTextArea(facturasImpre);
    }

    public double calcularIngreso(Factura[] facturas) {
        //método para sumar los las facturas
        int contClientes = 0;
        double ingresoDiario = 0;
        for (int i = 0; i < facturas.length; i++) {
            ingresoDiario += facturas[i].getCostoTotal();
            contClientes++;
        }
        LocalDateTime FechaHora = facturas[0].getFechaHora();
        Caja ingresosDelDia = new Caja(ingresoDiario, FechaHora, facturas);
        ingresosDiarios[contIngresosDiarios++] = ingresosDelDia;
        return ingresoDiario;
    }

    public Factura[] compararFechas(Factura[] facturas) {
        int contConicidencias = 0;
        String fechActual = LocalDateTime.now().getDayOfMonth()+"/"+LocalDateTime.now().getMonthValue()+"/"+LocalDateTime.now().getYear();
        if (facturas.length == 0) {
            m.imprimeMensaje("No hay ningún usuario, agregue al menos uno");
            inicializarArreglo();
        }
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] != null) {
                String fecHora = facturas[i].getFecha();
                if (fecHora.toLowerCase().contains(fechActual)) {
                    contConicidencias++;
                }
            }
        }
        if (contConicidencias == 0) {
            return null;
        }
        int contador = 0;
        Factura arregloCoincidencias[] = new Factura[contConicidencias];
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] != null) {
                String fecHora = facturas[i].getFechaHora().getDayOfMonth() + "/" + facturas[i].getFechaHora().getMonthValue() + "/" + facturas[i].getFechaHora().getYear();
                if (fecHora.toLowerCase().contains(fechActual)) {
                    Factura coincidencia = facturas[i];
                    arregloCoincidencias[contador++] = coincidencia;
                }
            }
        }
        return arregloCoincidencias;
    }
}
