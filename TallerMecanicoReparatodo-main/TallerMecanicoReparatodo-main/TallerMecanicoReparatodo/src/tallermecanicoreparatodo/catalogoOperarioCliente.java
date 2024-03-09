package tallermecanicoreparatodo;

public class CatalogoOperarioCliente {

    Miscelaneos m = new Miscelaneos();
    private OperarioCliente personas[];
    private int contPersonas = 0;

    public CatalogoOperarioCliente(int cantPersonas) {
        personas = new OperarioCliente[cantPersonas];
    }

    public void agregarPersona() {
        if (contPersonas == personas.length){
            m.imprimeMensaje("Ha llegado al límite de personas");
        } else {
        String nombre = m.recibeString("Ingrese el nombre: ");
        String primerApellido = m.recibeString("Ingrese el primer apellido: ");
        String segundoApellido = m.recibeString("Ingrese el segundo apellido: ");
        String ciudad = m.recibeString("Ingrese la ciudad: ");
        String direccion = m.recibeString("Ingrese la dirección: ");
        String telefono = m.recibeString("Ingrese el teléfono: ");
        String correoElec = m.recibeString("Ingrese el correo electrónico: ");
        boolean estado = true;
        OperarioCliente nuevaPersona = new OperarioCliente(nombre, primerApellido, segundoApellido, ciudad, direccion, telefono, correoElec, estado);
        personas[contPersonas++] = nuevaPersona;
        }
    }

    public void editarPersona(OperarioCliente persona) {
        //Sub menu de selección del dato a editar
        int opcion;
        do {            
            opcion = m.recibeInt("Seleccione el dato que desea editar:"
                    + "\n(1) Nombre"
                    + "\n(2) Primer Apellido"
                    + "\n(3) Segundo Apellido"
                    + "\n(4) Ciudad"
                    + "\n(5) Dirección"
                    + "\n(6) Teléfono"
                    + "\n(7) Email"
                    + "\n(0) Salir");
            switch (opcion) {
                case 1:
                    persona.setNombre(m.recibeString("Ingrese el nuevo nombre: "));
                    break;
                case 2:
                    persona.setPrimerApellido(m.recibeString("Ingrese el nuevo apellido: "));
                    break;
                case 3:
                    persona.setSegundoApellido(m.recibeString("Ingrese el nuevo apellido: "));
                    break;
                case 4:
                    persona.setCiudad(m.recibeString("Ingrese la nueva ciudad: "));
                    break;
                case 5:
                    persona.setDireccion(m.recibeString("Ingrese la nueva dirección: "));
                    break;
                case 6:
                    persona.setTelefono(m.recibeString("Ingrese el nuevo teléfono: "));
                    break;
                case 7:
                    persona.setCorreoElec(m.recibeString("Ingrese el nuevo email: "));
                    break;
                case 0:
                    m.imprimeMensaje("Volviendo al catálogo");
                    break;
                default:
                    m.imprimeMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }

    public void inactivarPersona(OperarioCliente persona) {
        if (persona.getEstado()== false){
            m.imprimeMensaje("El cliente seleccionado ya se encuentra inactivo");
        } else {
            persona.setEstado(false);
            m.imprimeMensaje("El cliente "+persona.getNombre()+", ha sido inactivado");
        }
    }
    
    public OperarioCliente[] consultarPersona(String busqueda) {
        if(contPersonas == 0){
            m.imprimeMensaje("Catálogo vacío");
            agregarPersona();
        }
        int contConicidencias = 0;
        for (int i = 0; i < contPersonas; i++) {
            if (personas[i].buscarCampos(busqueda)) {
                contConicidencias++;
            }
        }
        if (contConicidencias == 0) {
            return null;
        }
        int contador = 0;

        OperarioCliente arregloCoincidencias[] = new OperarioCliente[contConicidencias];
        for (int i = 0; i < contPersonas; i++) {
            if (personas[i].buscarCampos(busqueda)) {
                OperarioCliente coincidencia = personas[i];
                arregloCoincidencias[contador++] = coincidencia;
            }
        }
        return arregloCoincidencias;
    }
    
    public OperarioCliente listarSeleccionarPersona(OperarioCliente[] lista, boolean seleccion) {
        int contador = lista.length;
        String titulos[] = {"Nombre", "Primer Apellido", "Segundo Apellido", "Ciudad", "Dirección", "Teléfono", "Email", "Estado"};
        String[][] contenido = new String[contador][];
        for (int i = 0; i < contador; i++) {
            if (lista[i] != null) {
                //Se utiliza un if corto "? true : false " en el estado
                contenido[i] = new String[]{lista[i].getNombre(), lista[i].getPrimerApellido(), lista[i].getSegundoApellido(), lista[i].getCiudad(), lista[i].getDireccion(), lista[i].getTelefono(), lista[i].getCorreoElec(), lista[i].getEstado() ? "activo" : "inactivo"};
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
