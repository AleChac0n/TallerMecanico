package tallermecanicoreparatodo;

/**
 *
 * @author danny
 */
public class MenuPrincipal {

    Miscelaneos m = new Miscelaneos();
    Catalogo cat = new Catalogo();
    AdministradorUsuario admU = new AdministradorUsuario();
    AdministradorFactura admF = new AdministradorFactura(100);
    AdministradorCaja admC = new AdministradorCaja();

    //método que muestra el ménu principal
    public void mostrarMenu() {
        int opcion = 0;

        do {
            opcion = m.recibeInt("Seleccione una opción:\n"
                    + "(1) Administrar usuarios\n"
                    + "(2) Administrar catálogos\n"
                    + "(3) Administrar facturas\n"
                    + "(4) Administrar cajas\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1:
                    menuAdminUsuarios();
                    break;
                case 2:
                    cat.menuAdminCatalogos();
                    break;
                case 3:
                    menuAdminFacturas();
                    break;
                case 4:
                    menuAdminCaja();
                    break;
                case 0:
                    m.imprimeMensaje("Que tenga un buen día!");
                    break;
                default:
                    m.imprimeMensaje("La opción seleccionada es inválida");
            }
        } while (opcion != 0);
    }
    
    //método exclusivo para el control de manejo de usuarios(cajeros/administardores)
    public void menuAdminUsuarios() {
        Usuario resultados[];
        int opcion = 0;

        admU.inicializarArreglo();
        do {
            opcion = m.recibeInt("Seleccione una opción\n"
                    + "(1) Agregar Usuario\n"
                    + "(2) Consultar Usuario\n"
                    + "(3) Inactivar Usuario\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1:
                    admU.agregarUsuario();
                    break;
                case 2: {
                    String busqueda = m.recibeString("Ingrese algún dato referente al usuario (nombre, apellido, usuario)");
                    resultados = admU.consultarUsuario(busqueda);
                    if (resultados == null) {
                        m.imprimeMensaje("No se encontraron coincidencias, registre al usuario!");
                        break;
                    } else {
                        admU.listarSeleccionarUsuario(resultados, false);
                        break;
                    }
                }
                case 3: {
                    String busqueda = m.recibeString("Ingrese algún dato referente al usuario (nombre, apellido, usuario)");
                    resultados = admU.consultarUsuario(busqueda);
                    if (resultados == null) {
                        m.imprimeMensaje("No se encontraron coincidencias, registre al usuario!");
                        break;
                    } else {
                        Usuario usuarioSeleccionado = admU.listarSeleccionarUsuario(resultados, true);
                        admU.inactivarUsuario(usuarioSeleccionado);
                        break;
                    }
                }
                case 0:
                    m.imprimeMensaje("Volviendo al menu principal");
                    break;
                default:
                    m.imprimeMensaje("Opción incorrecta");
                    break;
            }
        } while (opcion != 0);
    }
    
    //método para desplegar ménu de administración de facturas
    public void menuAdminFacturas() {
        Factura resultados[];
        int opcion;

        do {
            opcion = m.recibeInt("Seleccione una opción:\n"
                    + "(1) Emitir Factura\n"
                    + "(2) Anular Factura\n"
                    + "(3) Mostrar Factura\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1: {
                    String busqueda;
                    Usuario[] resUsuario;
                    OperarioCliente[] resOperario;
                    OperarioCliente[] resCliente;
                    MarcaVehiculo[] resMarca;
                    Vehiculo[] resVehiculo;
                    Reparacion[] resReparacion;
                    do {
                        busqueda = m.recibeString("Ingrese algún dato referente al usuario (nombre, apellido, usuario)");
                        resUsuario = admU.consultarUsuario(busqueda);
                        if (resUsuario == null) {
                            m.imprimeMensaje("No se encontraron coincidencias");
                        }
                    } while (resUsuario == null);
                    Usuario usuarioSeleccionado = admU.listarSeleccionarUsuario(resUsuario, true);
                    do {
                        busqueda = m.recibeString("Ingrese algún dato referente al operario (nombre, apellido, usuario)");
                        resOperario = cat.getCatOperario().consultarPersona(busqueda);
                        if (resOperario == null) {
                            m.imprimeMensaje("No se encontraron coincidencias");
                        }
                    } while (resOperario == null);
                    OperarioCliente operarioSeleccionado = cat.getCatOperario().listarSeleccionarPersona(resOperario, true);
                    do {
                        busqueda = m.recibeString("Ingrese algún dato referente al cliente (nombre, apellido, usuario)");
                        resCliente = cat.getCatCliente().consultarPersona(busqueda);
                        if (resCliente == null) {
                            m.imprimeMensaje("No se encontraron coincidencias");
                        }
                    } while (resCliente == null);
                    OperarioCliente clienteSeleccionado = cat.getCatCliente().listarSeleccionarPersona(resCliente, true);
                    
                    MarcaVehiculo marcaSeleccionada = null;
                    if (cat.getCatVehiculo().getContVehiculos() == 0) {
                        do {
                            busqueda = m.recibeString("Ingrese algún dato referente a la marca (marca, pais de origen o caracteristicas)");
                            resMarca = cat.getCatMarcaVeh().consultarMarcaVehiculo(busqueda);
                            if (resMarca == null) {
                            }
                        } while (resMarca == null);
                        marcaSeleccionada = cat.getCatMarcaVeh().listarSeleccionarMarcaVehiculo(resMarca, true);
                    }
                    do {
                        busqueda = m.recibeString("Ingrese algún dato referente al vehiculo estilo");
                        resVehiculo = cat.getCatVehiculo().consultarVehiculo(busqueda);
                        if (resVehiculo == null) {
                            m.imprimeMensaje("No se encontraron coincidencias");
                            if (cat.getCatVehiculo().getContVehiculos() == 0) {
                                resVehiculo = cat.getCatVehiculo().consultarVehiculoPorMarca(marcaSeleccionada);
                            }
                        }
                    } while (resVehiculo == null);
                    Vehiculo vehiculoSeleccionado = cat.getCatVehiculo().listarSeleccionarVehiculo(resVehiculo, true);

                    int cantRep = m.recibeInt("¿Cuántas reparaciones desea agregar a la factura?");
                    Factura f = new Factura(cantRep);
                    f.setUsuario(usuarioSeleccionado);
                    f.setOperario(operarioSeleccionado);
                    f.setCliente(clienteSeleccionado);
                    f.setVehiculo(vehiculoSeleccionado);
                    for (int i = 0; i < cantRep; i++) {
                        do {
                            busqueda = m.recibeString("Ingrese algún dato referente a la reparación (daño o área afectada)");
                            resReparacion = cat.getCatReparacion().consultarReparacion(busqueda);
                            if (resReparacion == null) {
                                m.imprimeMensaje("No se encontraron coincidencias");
                            }
                        } while (resReparacion == null);
                        Reparacion reparacionSeleccionada = cat.getCatReparacion().listarSeleccionarReparacion(resReparacion, true);
                        f.getReparaciones()[i] = reparacionSeleccionada;
                    }
                    admF.emitirFactura(f);
                    break;
                }
                case 2:
                    if (admF.getContFact() == 0) {
                        m.imprimeMensaje("No hay facturas registradas para anular");
                    } else {
                        int posFactura = admF.listarSeleccionarFacturaAnular(true);
                        admF.anularFactura(posFactura);
                        break;
                    }
                case 3:
                    String busqueda = m.recibeString("Ingrese algún dato referente a la factura (usuario, cliente, operario)");
                    resultados = admF.consultarFactura(busqueda);
                    if (resultados == null) {
                        m.imprimeMensaje("No se encontraron coincidencias, registre al usuario!");
                        break;
                    } else {
                        Factura facturaSeleccionada = admF.listarSeleccionarFacturaMostrar(resultados, true);
                        admF.mostrarFactura(facturaSeleccionada);
                        break;
                    }
                case 0:
                    m.imprimeMensaje("Volviendo al menú principal");
                    break;
                default:
                    m.imprimeMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }
    
    //método para desplegar ménu de administración de caja(administardo por el usuario)
    public void menuAdminCaja() {
        Factura resulFact[];
        Factura totalFacturas[];
        double sumaIngresos;
        Caja resulCaja[];
        int opcion = 0;

        do {
            opcion = m.recibeInt("Seleccione una opción\n"
                    + "(1) Calcular Ingresos del Día\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1: {
                    totalFacturas = admF.getFacturas();
                    resulFact = admC.compararFechas(totalFacturas);
                    if (resulFact == null) {
                        m.imprimeMensaje("No se encontraron coincidencias");
                        break;
                    } else {
                        admC.inicializarArreglo();
                        sumaIngresos = admC.calcularIngreso(resulFact);
                        admC.mostrarIngreso(resulFact, sumaIngresos);
                        break;
                    }

                }
                case 0:
                    m.imprimeMensaje("Volviendo al menu principal");
                    break;
                default:
                    m.imprimeMensaje("Opción incorrecta");
                    break;
            }
        } while (opcion != 0);
    }
}
