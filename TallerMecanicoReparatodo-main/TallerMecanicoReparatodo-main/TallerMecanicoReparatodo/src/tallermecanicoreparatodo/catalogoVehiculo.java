package tallermecanicoreparatodo;

public class CatalogoVehiculo {
    
    Miscelaneos m = new Miscelaneos();
    private Vehiculo vehiculos[];
    private int contVehiculos = 0;
    
    public CatalogoVehiculo(int cantVehiculos) {
        vehiculos = new Vehiculo[cantVehiculos];
    }
    
    public void agregarVehiculo(MarcaVehiculo marca) {
        //método para agregar vehiculo
        if (contVehiculos == vehiculos.length) {
            m.imprimeMensaje("Ha llegado al límite de vehiculos");
        } else {
            String estilo = m.recibeString("Ingrese el estilo: ");
            boolean estado = true;
            for (int i = 0; i < contVehiculos; i++) {
                if (vehiculos[i].getEstilo().equalsIgnoreCase(estilo) && vehiculos[i].getMarcaVehiculos().getMarca().equalsIgnoreCase(marca.getMarca())){
                    m.imprimeMensaje("El estilo de esa marca ya ha sido registrado");
                    return;
                }
            }
            Vehiculo nuevoVehiculo = new Vehiculo(marca, estilo, estado);
            vehiculos[contVehiculos++] = nuevoVehiculo;
        }
    }    

    public void editarVehiculo(Vehiculo vehiculo) {
        //método para editar vehiculo
        int opcion;
        do {
            opcion = m.recibeInt("Seleccione el dato que desea editar:"
                    + "\n(1) Marca"
                    + "\n(2) País de Origen"
                    + "\n(3) Características"
                    + "\n(4) Estilo"
                    + "\n(0) Salir");
            switch (opcion) {
                case 1:
                    vehiculo.getMarcaVehiculos().setMarca(m.recibeString("Ingrese la nueva marca: "));
                    break;
                case 2:
                    vehiculo.getMarcaVehiculos().setPaisOringen(m.recibeString("Ingrese el país de origen: "));
                    break;
                case 3:
                    vehiculo.getMarcaVehiculos().setCaracteristicas(m.recibeString("Ingrese las nuevas características: "));
                    break;
                case 4:
                    vehiculo.setEstilo(m.recibeString("Ingrese el nuevo estilo: "));
                    break;
                case 0:
                    m.imprimeMensaje("Volviendo al catálogo");
                    break;
                default:
                    m.imprimeMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }
    
    public void inactivarVehiculo(Vehiculo vehiculo) {
        if (vehiculo.getEstado() == false) {
            m.imprimeMensaje("El vehiculo seleccionado ya se encuentra inactiva");
        } else {
            vehiculo.setEstado(false);
            m.imprimeMensaje("El vehiculo " + vehiculo.getEstilo() + ", ha sido inactivada");
        }
    }
    
    public Vehiculo[] consultarVehiculo(String busqueda) {
        int contConicidencias = 0;
        for (int i = 0; i < contVehiculos; i++) {
            if ((vehiculos[i].buscarCampos(busqueda) || vehiculos[i].getMarcaVehiculos().buscarCampos(busqueda))) {
                contConicidencias++;
            }
        }
        if (contConicidencias == 0) {
            return null;
        }
        int contador = 0;
        
        Vehiculo arregloCoincidencias[] = new Vehiculo[contConicidencias];
        for (int i = 0; i < contVehiculos; i++) {
            if (vehiculos[i].buscarCampos(busqueda) || vehiculos[i].getMarcaVehiculos().buscarCampos(busqueda)) {
                Vehiculo coincidencia = vehiculos[i];
                arregloCoincidencias[contador++] = coincidencia;
            }
        }
        return arregloCoincidencias;
    }
    public Vehiculo[] consultarVehiculoPorMarca(MarcaVehiculo marca) {
        int contConicidencias = 0;
        if(contVehiculos == 0){
            m.imprimeMensaje("Catálogo vacío");
            agregarVehiculo(marca);
        }
        String busqueda = marca.getMarca();
        for (int i = 0; i < contVehiculos; i++) {
            if (vehiculos[i].buscarCampos(busqueda)) {
                contConicidencias++;
            }
        }
        if (contConicidencias == 0) {
            return null;
        }
        int contador = 0;
        
        Vehiculo arregloCoincidencias[] = new Vehiculo[contConicidencias];
        for (int i = 0; i < contVehiculos; i++) {
            if (vehiculos[i].buscarCampos(busqueda)) {
                Vehiculo coincidencia = vehiculos[i];
                arregloCoincidencias[contador++] = coincidencia;
            }
        }
        return arregloCoincidencias;
    }
    
    public Vehiculo listarSeleccionarVehiculo(Vehiculo[] lista, boolean seleccion) {
        int contador = lista.length;
        String titulos[] = {"Marca", "Estilo"};
        String[][] contenido = new String[contador][];
        for (int i = 0; i < contador; i++) {
            if (lista[i] != null) {
                //Se utiliza un if corto "? true : false " en el estado
                contenido[i] = new String[]{lista[i].getMarcaVehiculos().getMarca(), lista[i].getEstilo()};
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

    public int getContVehiculos() {
        return contVehiculos;
    }

    public void setContVehiculos(int contVehiculos) {
        this.contVehiculos = contVehiculos;
    }
    
    
}
