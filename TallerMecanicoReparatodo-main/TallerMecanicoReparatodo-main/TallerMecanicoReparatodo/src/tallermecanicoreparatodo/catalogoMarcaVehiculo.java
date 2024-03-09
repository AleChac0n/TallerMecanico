package tallermecanicoreparatodo;

public class CatalogoMarcaVehiculo {

    Miscelaneos m = new Miscelaneos();
    private MarcaVehiculo marcasVehiculos[];
    private int contMarcaVeh = 0;

    public CatalogoMarcaVehiculo(int cantMarcasVehiculos) {
        marcasVehiculos = new MarcaVehiculo[cantMarcasVehiculos];
    }

    public void agregarMarcaVehiculo() {
        if (contMarcaVeh == marcasVehiculos.length){
            m.imprimeMensaje("Ha llegado al límite de marcas");
        } else {
        String marca = m.recibeString("Ingrese la marca: ");
        String paisOrigen = m.recibeString("Ingrese el país de origen: ");
        String caracteristicas = m.recibeString("Ingrese las caracteristicas: ");
        boolean estado = true;
        MarcaVehiculo nuevaMarca = new MarcaVehiculo(marca, paisOrigen, caracteristicas, estado);
        marcasVehiculos[contMarcaVeh++] = nuevaMarca;
        }
    }

    public void editarMarcaVehiculo(MarcaVehiculo marca) {
        //método para editar marca del vehiculo
        int opcion;
        do {
            opcion = m.recibeInt("Seleccione el dato que desea editar:"
                    + "\n(1) Marca"
                    + "\n(2) Pais de Origen"
                    + "\n(3) Caracteristicas"
                    + "\n(0) Salir");
            switch (opcion) {
                case 1:
                    marca.setMarca(m.recibeString("Ingrese una nueva marca: "));
                    break;
                case 2:
                    marca.setPaisOringen(m.recibeString("Ingrese el nuevo pais de origen: "));
                    break;
                case 3:
                    marca.setCaracteristicas(m.recibeString("Ingrese las nuevas carcteristicas: "));
                    break;
                case 0:
                    m.imprimeMensaje("Volviendo al catálogo");
                    break;
                default:
                    m.imprimeMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }

    public void inactivarMarcaVehiculo(MarcaVehiculo marca) {
        if (marca.getEstado() == false) {
            m.imprimeMensaje("La marca seleccionado ya se encuentra inactiva");
        } else {
            marca.setEstado(false);
            m.imprimeMensaje("La marca " + marca.getMarca() + ", ha sido inactivada");
        }
    }

    public MarcaVehiculo[] consultarMarcaVehiculo(String busqueda) {
        int contConicidencias = 0;
        if(contMarcaVeh == 0){
            m.imprimeMensaje("No hay ninguna marca, agregue al menos una");
            agregarMarcaVehiculo();
        }
        for (int i = 0; i < contMarcaVeh; i++) {
            if (marcasVehiculos[i].buscarCampos(busqueda)) {
                contConicidencias++;
            }
        }
        if (contConicidencias == 0) {
            return null;
        }
        int contador = 0;

        MarcaVehiculo arregloCoincidencias[] = new MarcaVehiculo[contConicidencias];
        for (int i = 0; i < contMarcaVeh; i++) {
            if (marcasVehiculos[i].buscarCampos(busqueda)) {
                MarcaVehiculo coincidencia = marcasVehiculos[i];
                arregloCoincidencias[contador++] = coincidencia;
            }
        }
        return arregloCoincidencias;
    }

    public MarcaVehiculo listarSeleccionarMarcaVehiculo(MarcaVehiculo[] lista, boolean seleccion) {
        int contador = lista.length;
        String titulos[] = {"Marca", "Pais de Origen", "Caracteristicas", "Estado"};
        String[][] contenido = new String[contador][];
        for (int i = 0; i < contador; i++) {
            if (lista[i] != null) {
                //Se utiliza un if corto "? true : false " en el estado
                contenido[i] = new String[]{lista[i].getMarca(), lista[i].getPaisOringen(), lista[i].getCaracteristicas(), lista[i].getEstado() ? "activo" : "inactivo"};
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

}
