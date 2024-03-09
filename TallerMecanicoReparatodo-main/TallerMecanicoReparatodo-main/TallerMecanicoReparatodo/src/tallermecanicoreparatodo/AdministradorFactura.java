package tallermecanicoreparatodo;

import java.time.LocalDateTime;

public class AdministradorFactura {

    Miscelaneos m = new Miscelaneos();
    private Factura facturas[];
    private int contFact = 0;

    public AdministradorFactura(int tamano) {
        facturas = new Factura[tamano];
    }

    public void emitirFactura(Factura factura) {
        //método que se encargará de emitir una factura por cliente
        factura.calcularCostoTotal();
        facturas[contFact++] = factura;
        m.imprimeMensaje("La factura ha sido emitida correctamente");
    }

    public void anularFactura(int posicionFactura) {
        int opc;
        do {
            opc = m.recibeInt("¿Está seguro que desea anular la factura?\n"
                    + "(1)Sí\n"
                    + "(2)No");
            if (opc == 1) {
                for (int i = posicionFactura; i < contFact - 1; i++) {
                    facturas[i] = facturas[i + 1];
                }
                if (contFact > 0) {
                    facturas[contFact - 1] = null;
                    contFact--;
                }
            }
            else{
                break;
            }
        } while (opc != 1 || opc != 2);
    }

    public void mostrarFactura(Factura factura) {
        LocalDateTime fecHora = factura.getFechaHora();
        String impFactura = String.format("%47s\n%47s\n\n", "Taller Reparatodo", "*************************************");
        impFactura += "Usuario:  " + factura.getUsuario().getNombre() + "\n";
        impFactura += "Operario: " + factura.getOperario().getNombre() + "\n";
        impFactura += "Cliente:  " + factura.getCliente().getNombre() + " " + factura.getCliente().getPrimerApellido() + "\n";
        impFactura += "Fecha: "+fecHora.getDayOfMonth() + "/" + fecHora.getMonthValue() + "/" + fecHora.getYear() + "\n";
        impFactura += factura.getVehiculo().getMarcaVehiculos().getMarca() + " " + factura.getVehiculo().getEstilo() + "\n";
        impFactura += String.format("%10s\t%20s\t%12s\n", "Área afectada", "Daño", "Precio");
        for (int i = 0; i < factura.getReparaciones().length; i++) {
            Reparacion r = factura.getReparaciones()[i];

            impFactura += String.format("%10s\t%20s\t%9.2f\n", r.getAreaAfectada(), r.getDano(), r.getPrecio());
        }
        impFactura += String.format("Precio Total: %.2f", factura.getCostoTotal());
        m.imprimeMensajeTextArea(impFactura);
    }

    public Factura[] consultarFactura(String busqueda) {
        if (contFact == 0) {
            m.imprimeMensaje("Catálogo vacío");
        }
        int contConicidencias = 0;
        for (int i = 0; i < contFact; i++) {
            if (facturas[i].buscarCampos(busqueda)) {
                contConicidencias++;
            }
        }
        if (contConicidencias == 0) {
            return null;
        }
        int contador = 0;

        Factura arregloCoincidencias[] = new Factura[contConicidencias];
        for (int i = 0; i < contFact; i++) {
            if (facturas[i].buscarCampos(busqueda)) {
                Factura coincidencia = facturas[i];
                arregloCoincidencias[contador++] = coincidencia;
            }
        }
        return arregloCoincidencias;
    }

    public int listarSeleccionarFacturaAnular(boolean seleccion) {
        int contador = contFact;
        String titulos[] = {"Usuario", "Operario", "Cliente", "Vehiculo", "Fecha"};
        String[][] contenido = new String[contador][];
        for (int i = 0; i < contador; i++) {
            if (facturas[i] != null) {
                //Se utiliza un if corto "? true : false " en el estado
                String fecHora = facturas[i].getFecha();
                contenido[i] = new String[]{facturas[i].getUsuario().getNombre(), facturas[i].getOperario().getNombre() + " " + facturas[i].getOperario().getPrimerApellido(), facturas[i].getCliente().getNombre() + " " + facturas[i].getCliente().getPrimerApellido(), facturas[i].getVehiculo().getMarcaVehiculos().getMarca()+" "+facturas[i].getVehiculo().getEstilo(), fecHora};
            } else {
                break;
            }
        }
        int fila = m.mostrarTabla(titulos, contenido);

        if (seleccion && fila == -1) {
            do {
                m.imprimeMensaje("Debe seleccionar un número");
                fila = m.mostrarTabla(titulos, contenido);
            } while (fila == -1);
        }
        return fila;
    }

    public Factura listarSeleccionarFacturaMostrar(Factura[] lista, boolean seleccion) {
        int contador = lista.length;
        String titulos[] = {"Usuario", "Operario", "Cliente", "Vehiculo", "Fecha"};
        String[][] contenido = new String[contador][];
        for (int i = 0; i < contador; i++) {
            if (lista[i] != null) {
                //Se utiliza un if corto "? true : false " en el estado
                String fecHora = facturas[i].getFechaHora().getDayOfMonth() + "/" + facturas[i].getFechaHora().getMonthValue() + "/" + facturas[i].getFechaHora().getYear();
                contenido[i] = new String[]{lista[i].getUsuario().getNombre(), lista[i].getOperario().getNombre() + " " + lista[i].getOperario().getPrimerApellido(), lista[i].getCliente().getNombre() + " " + lista[i].getCliente().getPrimerApellido(),facturas[i].getVehiculo().getMarcaVehiculos().getMarca()+" "+facturas[i].getVehiculo().getEstilo(), fecHora};
            } else {
                break;
            }
        }
        int fila = m.mostrarTabla(titulos, contenido);

        if (seleccion && fila == -1) {
            do {
                m.imprimeMensaje("Debe seleccionar un número");
                fila = m.mostrarTabla(titulos, contenido);
            } while (fila == -1);
        }
        if (fila != -1) {
            return lista[fila];
        }
        return null;
    }

    public int getContFact() {
        return contFact;
    }

    public void setContFact(int contFact) {
        this.contFact = contFact;
    }

    public Factura[] getFacturas() {
        return facturas;
    }

    public void setFacturas(Factura[] facturas) {
        this.facturas = facturas;
    }
    
    

}
