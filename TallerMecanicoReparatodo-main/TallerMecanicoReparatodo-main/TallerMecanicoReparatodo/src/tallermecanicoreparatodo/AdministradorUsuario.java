package tallermecanicoreparatodo;

public class AdministradorUsuario {

    Miscelaneos m = new Miscelaneos();
    private Usuario usuarios[];
    private int contU = 0;

    public AdministradorUsuario() {
    }

    public void inicializarArreglo() {
        if (usuarios == null) {
            int tamanoUsuario = m.recibeInt("Digite la cantidad de usuarios para el vector");
            //Incializar el vector usuarios
            usuarios = new Usuario[tamanoUsuario];
        }
    }

    public void agregarUsuario() {
        if (contU == usuarios.length){
            m.imprimeMensaje("Ha llegado al límite de usuarios");
        } else {
        Usuario nuevoUsuario = new Usuario();
        String nombre = m.recibeString("Ingrese el nombre: ");
        String primerApellido = m.recibeString("Ingrese el primer apellido: ");
        String segundoApellido = m.recibeString("Ingrese el segundo apellido: ");
        String usuario = m.recibeString("Cree su usuario: ");
        char tipo = 'O';
        String password = m.recibeString("Cree una contraseña: ");
        boolean estado = true;
        nuevoUsuario = new Usuario(nombre, primerApellido, segundoApellido, usuario, tipo, password, estado);
        usuarios[contU++] = nuevoUsuario;
        }
    }


    public Usuario[] consultarUsuario(String busqueda) {
        int contConicidencias = 0;
        if(contU == 0){
            m.imprimeMensaje("No hay ningún usuario, agregue al menos uno");
            inicializarArreglo();
            agregarUsuario();
        }
        for (int i = 0; i < contU; i++) {
            if (usuarios[i].buscarCampos(busqueda)) {
                contConicidencias++;
            }
        }
        if (contConicidencias == 0) {
            return null;
        }
        int contador = 0;
        Usuario arregloCoincidencias[] = new Usuario[contConicidencias];
        for (int i = 0; i < contU; i++) {
            if (usuarios[i].buscarCampos(busqueda)) {
                Usuario coincidencia = usuarios[i];
                arregloCoincidencias[contador++] = coincidencia;
            }
        }
        return arregloCoincidencias;
    }

    public Usuario listarSeleccionarUsuario(Usuario[] lista, boolean seleccion) {
        int contador = lista.length;
        String titulos[] = {"Nombre", "Primer Apellido", "Segundo Apellido", "Usuario", "Estado"};
        String[][] contenido = new String[contador][];
        for (int i = 0; i < contador; i++) {
            if (lista[i] != null) {
                contenido[i] = new String[]{lista[i].getNombre(), lista[i].getPrimerApellido(), lista[i].getSegundoApellido(), lista[i].getUsuario(), lista[i].getEstado() ? "activo" : "inactivo"};
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

    public void inactivarUsuario(Usuario usuario) {
        //se coloca la variable estado que sirve para inactivar a un usuario
        boolean estado = usuario.getEstado();
        if (estado) {
            usuario.setEstado(false);
        }
    }
    
}
