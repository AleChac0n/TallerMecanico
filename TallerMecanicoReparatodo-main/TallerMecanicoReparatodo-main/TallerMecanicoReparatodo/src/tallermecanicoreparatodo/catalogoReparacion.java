package tallermecanicoreparatodo;

public class CatalogoReparacion {
    Miscelaneos m = new Miscelaneos();
    private Reparacion reparaciones[];
    private int contRep=0;

    public CatalogoReparacion(int cantReparaciones) {
        reparaciones = new Reparacion[cantReparaciones];
    }

    public void agregarReparacion(){
        if (contRep == reparaciones.length){
            m.imprimeMensaje("Ha llegado al límite de reparaciones");
        } else {
        String dano = m.recibeString("Indique el daño: ");
        String areaAfectada = m.recibeString("Indique el area afectada: ");
        double precio = m.recibeDouble("Ingrese el costo del daño: ");
        Reparacion nuevaReparacion = new Reparacion(dano, areaAfectada, precio, true);
        reparaciones[contRep++] = nuevaReparacion;
        }
    }
    public void editarReparacion(Reparacion rep){
        //Sub menu de selección del dato a editar
        int opcion;
        do {            
            opcion = m.recibeInt("Seleccione el dato que desea editar:"
                    + "\n(1) Daño"
                    + "\n(2) Área Afectada"
                    + "\n(3) Precio"
                    + "\n(0) Salir");
            switch (opcion) {
                case 1:
                    rep.setDano(m.recibeString("Ingrese la modificación al daño"));
                    break;
                case 2:
                    rep.setAreaAfectada(m.recibeString("Ingrese la modificación al área afectada"));
                    break;
                case 3:
                    rep.setPrecio(m.recibeDouble("Ingrese el nuevo precio de la reparación"));
                    break;
                case 0:
                    m.imprimeMensaje("Volviendo al catálogo");
                    break;
                default:
                    m.imprimeMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }
    public void inactivarReparacion(Reparacion rep){
        //método para inacvtivar marca del reparación
        if (rep.getEstado()== false){
            m.imprimeMensaje("La reparación seleccionada ya se encuentra inactiva");
        } else {
            rep.setEstado(false);
            m.imprimeMensaje("La reparación a "+rep.getAreaAfectada()+", ha sido inactivado");
        }
    }
    
    public Reparacion[] consultarReparacion(String busqueda) {
        int contConicidencias = 0;
        if(contRep == 0){
            m.imprimeMensaje("Catálogo vacío");
            agregarReparacion();
        }
        for (int i = 0; i < contRep; i++) {
            if (reparaciones[i].buscarCampos(busqueda)) {
                contConicidencias++;
            }
        }
        if (contConicidencias == 0) {
            return null;
        }
        int contador = 0;

        Reparacion arregloCoincidencias[] = new Reparacion[contConicidencias];
        for (int i = 0; i < contRep; i++) {
            if (reparaciones[i].buscarCampos(busqueda)) {
                Reparacion coincidencia = reparaciones[i];
                arregloCoincidencias[contador++] = coincidencia;
            }
        }
        return arregloCoincidencias;
    }
    
    public Reparacion listarSeleccionarReparacion(Reparacion[] lista, boolean seleccion) {
        int contador = lista.length;
        String titulos[] = {"Daño", "Área Afectada", "Precio", "Estado"};
        String[][] contenido = new String[contador][];
        for (int i = 0; i < contador; i++) {
            if (lista[i] != null) {
                //Se utiliza un if corto "? true : false " en el estado
                double costo = lista[i].getPrecio();
                String precio = ""+costo+"";
                contenido[i] = new String[]{lista[i].getDano(), lista[i].getAreaAfectada(), precio, lista[i].getEstado() ? "activo" : "inactivo"};
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
        if (fila != -1){
            return lista[fila];
        }
        return null;
    }
    
}
