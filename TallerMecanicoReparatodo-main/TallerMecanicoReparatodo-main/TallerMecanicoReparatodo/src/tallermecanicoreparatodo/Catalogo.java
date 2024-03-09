package tallermecanicoreparatodo;

public class Catalogo {

    Miscelaneos m = new Miscelaneos();
    private CatalogoVehiculo catVehiculo;
    private CatalogoMarcaVehiculo catMarcaVeh;
    private CatalogoOperarioCliente catOperario;
    private CatalogoOperarioCliente catCliente;
    private CatalogoReparacion catReparacion;

    public Catalogo() {
    }

    public void inicializarCatalogos(int eleccionCatalogo) {
        switch (eleccionCatalogo) {
            case 1: {
                if (catCliente == null) {
                    int tamano = m.recibeInt("Digite el número máximo de clientes del catálogo");
                    //Incializar el vector clientes
                    catCliente = new CatalogoOperarioCliente(tamano);
                }
                break;
            }
            case 2: {
                if (catOperario == null) {
                    int tamano = m.recibeInt("Digite el número máximo de operarios en el catálogo");
                    //Incializar el vector operarios
                    catOperario = new CatalogoOperarioCliente(tamano);
                }
                break;
            }
            case 3: {
                if (catVehiculo == null) {
                    int tamano = m.recibeInt("Digite el número máximo de vehiculos en el catálogo");
                    //Incializar el vector vehiculos
                    catVehiculo = new CatalogoVehiculo(tamano);
                }
                break;
            }
            case 4: {
                if (catMarcaVeh == null) {
                    int tamano = m.recibeInt("Digite el número máximo de marcas de vehiculos en el catálogo");
                    //Incializar el vector marcaVehiculos
                    catMarcaVeh = new CatalogoMarcaVehiculo(tamano);
                }
                break;
            }
            case 5: {
                if (catReparacion == null) {
                    int tamano = m.recibeInt("Digite el número máximo de reparaciones en el catálogo");
                    //Incializar el vector reparacion
                    catReparacion = new CatalogoReparacion(tamano);
                }
                break;
            }
            default:
                break;
        }
    }

    public CatalogoVehiculo getCatVehiculo() {
        inicializarCatalogos(3);
        return catVehiculo;
    }

    public void setCatVehiculo(CatalogoVehiculo catVehiculo) {
        this.catVehiculo = catVehiculo;
    }

    public CatalogoMarcaVehiculo getCatMarcaVeh() {
        inicializarCatalogos(4);
        return catMarcaVeh;
    }

    public void setCatMarcaVeh(CatalogoMarcaVehiculo catMarcaVeh) {
        this.catMarcaVeh = catMarcaVeh;
    }

    public CatalogoOperarioCliente getCatOperario() {
        inicializarCatalogos(2);
        return catOperario;
    }

    public void setCatOperario(CatalogoOperarioCliente catOperario) {
        this.catOperario = catOperario;
    }

    public CatalogoOperarioCliente getCatCliente() {
        inicializarCatalogos(1);
        return catCliente;
    }

    public void setCatCliente(CatalogoOperarioCliente catCliente) {
        this.catCliente = catCliente;
    }

    public CatalogoReparacion getCatReparacion() {
        inicializarCatalogos(5);
        return catReparacion;
    }

    public void setCatReparacion(CatalogoReparacion catReparacion) {
        this.catReparacion = catReparacion;
    }

    public void menuAdminCatalogos() {
        int opcCata;
        OperarioCliente resultados[];

        do {
            opcCata = m.recibeInt("Seleccione el catálogo que desea administrar:\n"
                    + "(1) Catálogo de Clientes\n"
                    + "(2) Catálogo de Operarios\n"
                    + "(3) Catálogo de Vehiculos\n"
                    + "(4) Catálogo de Marcas de Vehiculos\n"
                    + "(5) Catálogo de Reparaciones\n"
                    + "(0) Salir\n");
            inicializarCatalogos(opcCata);
            switch (opcCata) {
                case 1:
                    menuCatClientes();
                    break;
                case 2:
                    menuCatOperarios();
                    break;
                case 3:
                    menuCatVehiculos();
                    break;
                case 4:
                    menuCatMarcaVehiculos();
                    break;
                case 5:
                    menuCatReparaciones();
                    break;
                case 0:
                    m.imprimeMensaje("Volviendo al menú principal");
                    break;
                default:
                    m.imprimeMensaje("La opción seleccionada es inválida");
            }
        } while (opcCata != 0);
    }

    public void menuCatClientes() {
        int opcion;
        OperarioCliente resultados[];
        if (catCliente == null) {
            m.imprimeMensaje("No se ha inicializado el catálogo de clientes");
            getCatCliente();
        }
        do {
            opcion = m.recibeInt("Seleccione una opción:\n"
                    + "(1) Agregar Cliente\n"
                    + "(2) Editar Cliente\n"
                    + "(3) Inactivar cliente\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1:
                    catCliente.agregarPersona();
                    break;
                case 2: {
                    //Editar cliente
                    int opcAgregar;
                    String busqueda = m.recibeString("Ingrese algún dato referente al cliente (nombre, apellido, ciudad, dirección, teléfono, etc.)");
                    resultados = getCatCliente().consultarPersona(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho cliente
                        m.imprimeMensaje("No se encontraron coincidencias, registre al cliente!");
                        do {
                            opcAgregar = m.recibeInt("¿Desea agregar al cliente?\n"
                                    + "(1)Sí\n"
                                    + "(2)No");
                            switch (opcAgregar) {
                                case 1:
                                    catCliente.agregarPersona();
                                    break;
                                case 2:
                                    break;
                                default:
                                    m.imprimeMensaje("La opción seleccionada es inválida");
                                    break;
                            }
                        } while (opcAgregar != 2);
                        break;
                    } else {
                        OperarioCliente clienteSeleccionado = catCliente.listarSeleccionarPersona(resultados, true);
                        catCliente.editarPersona(clienteSeleccionado);
                        break;
                    }
                }
                case 3: {
                    //Inactivar cliente
                    String busqueda = m.recibeString("Ingrese algún dato referente al cliente (nombre, apellido, ciudad, dirección, teléfono, etc.)");
                    resultados = catCliente.consultarPersona(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho cliente
                        m.imprimeMensaje("No se encontraron coincidencias, registre al cliente!");
                        break;
                    } else {
                        OperarioCliente clienteSeleccionado = catCliente.listarSeleccionarPersona(resultados, true);
                        catCliente.inactivarPersona(clienteSeleccionado);
                        break;
                    }
                }
                case 0:
                    m.imprimeMensaje("Volviendo al menu de catálogos");
                    break;
                default:
                    m.imprimeMensaje("La opción seleccionada es inválida");
            }
        } while (opcion != 0);
    }

    public void menuCatOperarios() {
        int opcion;
        OperarioCliente resultados[];
        if (catOperario == null) {
            m.imprimeMensaje("No se ha inicializado el catálogo de operarios");
            getCatOperario();
        }

        do {
            opcion = m.recibeInt("Seleccione una opción:\n"
                    + "(1) Agregar Operario\n"
                    + "(2) Editar Operario\n"
                    + "(3) Inactivar Operario\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1:
                    catOperario.agregarPersona();
                    break;
                case 2: {
                    //Editar cliente
                    int opcAgregar;
                    String busqueda = m.recibeString("Ingrese algún dato referente al operario (nombre, apellido, ciudad, dirección, teléfono, etc.)");
                    resultados = catOperario.consultarPersona(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho cliente
                        m.imprimeMensaje("No se encontraron coincidencias, registre al operario!");
                        do {
                            opcAgregar = m.recibeInt("¿Desea agregar al operario?\n"
                                    + "(1)Sí\n"
                                    + "(2)No");
                            switch (opcAgregar) {
                                case 1:
                                    catOperario.agregarPersona();
                                    break;
                                case 2:
                                    break;
                                default:
                                    m.imprimeMensaje("La opción seleccionada es inválida");
                                    break;
                            }
                        } while (opcAgregar != 2);
                        break;
                    } else {
                        OperarioCliente operarioSeleccionado = catOperario.listarSeleccionarPersona(resultados, true);
                        catOperario.editarPersona(operarioSeleccionado);
                        break;
                    }
                }
                case 3: {
                    //Inactivar cliente
                    String busqueda = m.recibeString("Ingrese algún dato referente al cliente (nombre, apellido, ciudad, dirección, teléfono, etc.)");
                    resultados = catCliente.consultarPersona(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho cliente
                        m.imprimeMensaje("No se encontraron coincidencias, registre al cliente!");
                        break;
                    } else {
                        OperarioCliente clienteSeleccionado = catCliente.listarSeleccionarPersona(resultados, true);
                        catCliente.inactivarPersona(clienteSeleccionado);
                        break;
                    }
                }
                case 0:
                    m.imprimeMensaje("Volviendo al menu de catálogos");
                    break;
                default:
                    m.imprimeMensaje("La opción seleccionada es inválida");
            }
        } while (opcion != 0);
    }

    public void menuCatReparaciones() {
        int opcion;
        Reparacion resultados[];
        if (catReparacion == null) {
            m.imprimeMensaje("No se ha inicializado el catálogo de reparaciones");
            getCatReparacion();
        }

        do {
            opcion = m.recibeInt("Seleccione una opción:\n"
                    + "(1) Agregar Reparación\n"
                    + "(2) Editar Reparación\n"
                    + "(3) Inactivar Reparación\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1:
                    catReparacion.agregarReparacion();
                    break;
                case 2: {
                    //Editar cliente
                    int opcAgregar;
                    String busqueda = m.recibeString("Ingrese algún dato referente a la reparación (daño o área afectada)");
                    resultados = catReparacion.consultarReparacion(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho cliente
                        m.imprimeMensaje("No se encontraron coincidencias, registre la reparación!");
                        do {
                            opcAgregar = m.recibeInt("¿Desea agregar la reparación?\n"
                                    + "(1)Sí\n"
                                    + "(2)No");
                            switch (opcAgregar) {
                                case 1:
                                    catReparacion.agregarReparacion();
                                    break;
                                case 2:
                                    break;
                                default:
                                    m.imprimeMensaje("La opción seleccionada es inválida");
                                    break;
                            }
                        } while (opcAgregar != 2);
                        break;
                    } else {
                        Reparacion reparSeleccionada = catReparacion.listarSeleccionarReparacion(resultados, true);
                        catReparacion.editarReparacion(reparSeleccionada);
                        break;
                    }
                }

                case 3: {
                    //Inactivar cliente
                    String busqueda = m.recibeString("Ingrese algún dato referente a la reparación (daño o área afectada)");
                    resultados = catReparacion.consultarReparacion(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho cliente
                        m.imprimeMensaje("No se encontraron coincidencias, registre al usuario!");
                        break;
                    } else {
                        Reparacion reparSeleccionada = catReparacion.listarSeleccionarReparacion(resultados, true);
                        catReparacion.inactivarReparacion(reparSeleccionada);
                        break;
                    }
                }
                case 0:
                    m.imprimeMensaje("Volviendo al menu de catálogos");
                    break;
                default:
                    m.imprimeMensaje("La opción seleccionada es inválida");
            }
        } while (opcion != 0);
    }

    public void menuCatMarcaVehiculos() {
        int opcion;
        MarcaVehiculo resultados[];
        if (catMarcaVeh == null) {
            m.imprimeMensaje("No se ha inicializado el catálogo de marcas de vehículo");
            getCatMarcaVeh();
        }

        do {
            opcion = m.recibeInt("Seleccione una opción:\n"
                    + "(1) Agregar Marca\n"
                    + "(2) Editar Marca\n"
                    + "(3) Inactivar Marca\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1:
                    catMarcaVeh.agregarMarcaVehiculo();
                    break;
                case 2: {
                    //Editar cliente
                    int opcAgregar;
                    String busqueda = m.recibeString("Ingrese algún dato referente a la marca (marca o país de origen)");
                    resultados = catMarcaVeh.consultarMarcaVehiculo(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho cliente
                        m.imprimeMensaje("No se encontraron coincidencias, registre la marca!");
                        do {
                            opcAgregar = m.recibeInt("¿Desea agregar la marca?\n"
                                    + "(1)Sí\n"
                                    + "(2)No");
                            switch (opcAgregar) {
                                case 1:
                                    catMarcaVeh.agregarMarcaVehiculo();
                                    break;
                                case 2:
                                    break;
                                default:
                                    m.imprimeMensaje("La opción seleccionada es inválida");
                                    break;
                            }
                        } while (opcAgregar != 2);
                        break;
                    } else {
                        MarcaVehiculo marcaSeleccionada = catMarcaVeh.listarSeleccionarMarcaVehiculo(resultados, true);
                        catMarcaVeh.editarMarcaVehiculo(marcaSeleccionada);
                        break;
                    }
                }

                case 3: {
                    //Inactivar cliente
                    String busqueda = m.recibeString("Ingrese algún dato referente a la marca (marca o país de origen)");
                    resultados = catMarcaVeh.consultarMarcaVehiculo(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho cliente
                        m.imprimeMensaje("No se encontraron coincidencias, registre la marca!");
                        break;
                    } else {
                        MarcaVehiculo marcaSeleccionada = catMarcaVeh.listarSeleccionarMarcaVehiculo(resultados, true);
                        catMarcaVeh.inactivarMarcaVehiculo(marcaSeleccionada);
                        break;
                    }
                }
                case 0:
                    m.imprimeMensaje("Volviendo al menu de catálogos");
                    break;
                default:
                    m.imprimeMensaje("La opción seleccionada es inválida");
            }
        } while (opcion != 0);
    }

    public void menuCatVehiculos() {
        int opcion;
        MarcaVehiculo resultadosMarca[];
        Vehiculo resultados[];
        if (catMarcaVeh == null) {
            m.imprimeMensaje("No se ha inicializado el catálogo de marcas de vehículo");
            getCatMarcaVeh();
        }
        if (catVehiculo == null) {
            m.imprimeMensaje("No se ha inicializado el catálogo de vehículos");
            getCatVehiculo();
        }

        do {
            opcion = m.recibeInt("Seleccione una opción:\n"
                    + "(1) Agregar Vehiculo\n"
                    + "(2) Editar Vehiculo\n"
                    + "(3) Inactivar Vehiculo\n"
                    + "(0) Salir\n");
            switch (opcion) {
                case 1: {
                    int opcAgregar;
                    String busqueda = m.recibeString("Ingrese algún dato referente a la marca (marca o país de origen)");
                    resultadosMarca = catMarcaVeh.consultarMarcaVehiculo(busqueda);
                    if (resultadosMarca == null) {
                        //Mensaje que indica que en este catálogo no existe dicha marca
                        m.imprimeMensaje("No se encontraron coincidencias, registre la marca!");
                        do {
                            opcAgregar = m.recibeInt("¿Desea agregar la marca?\n"
                                    + "(1)Sí\n"
                                    + "(2)No");
                            switch (opcAgregar) {
                                case 1:
                                    catMarcaVeh.agregarMarcaVehiculo();
                                    break;
                                case 2:
                                    break;
                                default:
                                    m.imprimeMensaje("La opción seleccionada es inválida");
                                    break;
                            }
                        } while (opcAgregar != 2 && opcAgregar != 1);
                        break;
                    } else {
                        MarcaVehiculo marcaSeleccionada = catMarcaVeh.listarSeleccionarMarcaVehiculo(resultadosMarca, true);
                        catVehiculo.agregarVehiculo(marcaSeleccionada);
                    }
                    break;
                }
                case 2: {
                    //Editar vehiculo
                    int opcAgregar;
                    String busqueda = m.recibeString("Ingrese algún dato referente al vehiculo (marca o país de origen) o estilo");
                    resultados = catVehiculo.consultarVehiculo(busqueda);
                    if (resultados == null) {
                        /*String busquedaMarca = busqueda;
                        resultadosMarca = catMarcaVeh.consultarMarcaVehiculo(busquedaMarca);
                        if (resultadosMarca == null) {
                            //Mensaje que indica que en este catálogo no existe dicho cliente
                            m.imprimeMensaje("No se encontraron coincidencias, registre la marca!");
                        } else {
                            Vehiculo vehiculoSeleccionado = catVehiculo.listarSeleccionarVehiculo(resultados, true);
                            catVehiculo.editarVehiculo(vehiculoSeleccionado);*/
                        m.imprimeMensaje("No hay coincidencias, registre el vehiculo");
                    } else {
                        Vehiculo vehiculoSeleccionado = catVehiculo.listarSeleccionarVehiculo(resultados, true);
                        catVehiculo.editarVehiculo(vehiculoSeleccionado);
                    }
                    break;
                }
            
            case 3: {
                    //Inactivar vehiculo
                    String busqueda = m.recibeString("Ingrese algún dato referente al vehiculo (marca o país de origen) o estilo)");
                    resultados = catVehiculo.consultarVehiculo(busqueda);
                    if (resultados == null) {
                        //Mensaje que indica que en este catálogo no existe dicho vehiculo
                        m.imprimeMensaje("No se encontraron coincidencias, registre al vehiculo!");
                        break;
                    } else {
                        Vehiculo vehiculoSeleccionado = catVehiculo.listarSeleccionarVehiculo(resultados, true);
                        catVehiculo.inactivarVehiculo(vehiculoSeleccionado);
                        break;
                    }
                }
                case 0:
                    m.imprimeMensaje("Volviendo al menu de catálogos");
                    break;
                default:
                    m.imprimeMensaje("La opción seleccionada es inválida");
            }
        } while (opcion != 0);
        }

    }
