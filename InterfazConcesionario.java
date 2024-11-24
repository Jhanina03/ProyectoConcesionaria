
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

public class InterfazConcesionario {

    private static Concesionario concesionario;
    private static Cliente clienteActual;

    public static void main(String[] args) {
        concesionario = new Concesionario();

        // Agregar autos para vender
        Cliente comprador = new Cliente("p", "p", "p", "p", "AV. Patate");
        concesionario.agregarAuto(new Auto("TBD-4513", "Hyundai", "Negro", 2020, "SUV", 24999, 3));
        concesionario.agregarAuto(new Auto("TBD-4513", "Nissan", "Celeste", 2020, "SUV", 20999, 2));
        concesionario.agregarAuto(new Auto("TDA-4524", "Chevrolet", "Rojo", 2020, "SUV", 19999, 7));
        concesionario.agregarAuto(new Auto("TDA-4524", "Chevrolet", "Blanco", 2020, "Sedan", 19999, 7));
        concesionario.agregarAuto(new Auto("CBH-4533", "Audi", "Rojo", 2020, "Deportivo", 49000, 1));
        concesionario.agregarAuto(new Auto("PKT-4254", "Toyota", "Morado", 2020, "Sedan", 15499, 20));
        concesionario.agregarAuto(new Auto("GBY-1435", "Volkswagen", "Gris", 2020, "Pickup", 11999, 10));
        concesionario.agregarAuto(new Auto("GBY-1421", "Volkswagen", "Plateado", 2020, "Pickup", 11999, 10));
        concesionario.agregarAuto(new Auto("PKT-4254", "Ferrari", "Rojo", 2020, "Deportivo", 150499, 20));
        concesionario.agregarAuto(new Auto("GBY-1455", "Maseratti", "Gris", 2020, "Deportivo", 40999, 10));
        concesionario.agregarAuto(new Auto("PKT-4254", "Porsche", "Negro", 2020, "SUV", 25499, 20));
        concesionario.agregarAuto(new Auto("GBY-1455", "Mazda", "Blanco", 2020, "Pickup", 15999, 10));
        concesionario.agregarCliente(new Cliente("1231", "Sebas", "Santamaria", "1231", "AV. Ambato"));
        concesionario.agregarCliente(new Cliente("1232", "Fernando", "Ibarra", "12333", "AV. Cuenca"));
        concesionario.agregarCliente(new Cliente("1233", "Felix", "Fernandez", "12344", "AV. Quito"));
        concesionario.agregarCliente(comprador);
        concesionario.registrarInteres(concesionario.clientes.primero.dato, concesionario.autos.primero.dato); // Sebas interesado en el primer auto
                                                                                                               
        concesionario.registrarInteres(concesionario.clientes.primero.dato, concesionario.autos.primero.siguiente.dato);// Sebas interesado en el segundo auto
                                                                                                                        
        concesionario
                .agregarVenta(new Venta(comprador, concesionario.autos.primero.dato, LocalDate.of(2022, 4, 19), 2000)); // Se elimina al interesado porque ya pasaron mas de 3 años
                                                                                                                        
        concesionario.agregarVenta(
                new Venta(comprador, concesionario.autos.primero.siguiente.dato, LocalDate.of(2021, 4, 19), 2000)); // Se muestra al intersado porque aun no han pasado mas de 3 años desde el interes
                                                                                                                   
        menuInicio();
    }

    public static void menuInicio() {
        int opcionInicio = 0;
        do {
            String seleccion = JOptionPane.showInputDialog(null,
                    "\tBienvenido a la Concesionaria\n\n1. Registrarse\n2. Iniciar Sesión\n3. Salir\n\nSeleccione una opción:\n",
                    "Menú Inicio", 3);
            if (seleccion == null) {
                return;
            }
            try {
                opcionInicio = Integer.parseInt(seleccion);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcionInicio) {
                case 1:
                    clienteActual = ingresarDatosCliente();
                    concesionario.agregarCliente(clienteActual);
                    menuConcesionaria();
                    break;
                case 2:
                    String nombre = JOptionPane.showInputDialog(null, "Nombre: ", "Inicio de Sesión",
                            JOptionPane.QUESTION_MESSAGE);
                    String cedula = JOptionPane.showInputDialog(null, "Cédula: ", "Inicio de Sesión",
                            JOptionPane.QUESTION_MESSAGE);
                    clienteActual = iniciarSesion(nombre, cedula);
                    if (iniciarSesion(nombre, cedula) == null) {
                        iniciarSesionAdmin(nombre, cedula);
                    } else {
                        concesionario.notificarVentas(clienteActual);
                        menuConcesionaria();
                    }
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Gracias por visitar la Concesionaria");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcionInicio != 3);
    }

    private static void menuAdmin() {
        int opcion = 0;
        do {
            String seleccion = JOptionPane.showInputDialog(null,
                    "\tBienvenido a la Concesionaria\n\n1. Autos Vendidos\n2. Ver Interesados en un Modelo"
                            + "\n3. Ver Ventas Filtradas por Fechas \n4. Salir a Menu Principal\n\nSeleccione una opción:\n",
                    "Menú Concesionaria", 3);
            if (seleccion == null) {
                return;
            }
            try {
                opcion = Integer.parseInt(seleccion);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                continue;
            }
            switch (opcion) {
                case 1:
                    imprimirAutosComprados();
                    break;
                case 2:
                    imprimirInteresados();
                    break;
                case 3:
                    imprimirInformeVentasPorRango();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Gracias por visitar la Concesionaria");
                    menuInicio();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 4);

    }

    public static void menuConcesionaria() {
        int opcion = 0;
        do {
            String seleccion = JOptionPane.showInputDialog(null,
                    "\tBienvenido a la Concesionaria\n\n1. Buscar auto\n2. Comprar Auto\n3. Generar informe del cliente\n4. Salir a menu inicio sesión\n\nSeleccione una opción:\n",
                    "Menú Concesionaria", 3);
            if (seleccion == null) {
                return;
            }
            try {
                opcion = Integer.parseInt(seleccion);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                continue;
            }
            switch (opcion) {
                case 1:
                    buscarAuto();
                    break;
                case 2:
                    ventaAuto();
                    break;
                case 3:
                    generarInformeCliente();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Gracias por visitar la Concesionaria");
                    menuInicio();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 4);
    }

    private static void iniciarSesionAdmin(String nombre, String cedula) {
        boolean autenticado = concesionario.admin.verificarCredenciales(nombre, cedula);
        if (autenticado) {
            JOptionPane.showMessageDialog(null, "Bienvenido Admin");
            menuAdmin();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar a la persona");

        }
    }

    public static Cliente iniciarSesion(String nombre, String cedula) {

        Cliente clienteEncontrado = concesionario.buscarClienteCedula(cedula);
        if (clienteEncontrado != null && clienteEncontrado.getNombre().equalsIgnoreCase(nombre)) {
            return clienteEncontrado;
        } else {
            return null;
        }
    }

    public static Cliente ingresarDatosCliente() {
        String cedula = null;
        String nombre = null;
        String apellido = null;
        String telefono = null;
        String direccion = null;

        boolean datosCompletos = false;

        while (!datosCompletos) {
            cedula = JOptionPane.showInputDialog(null, "Cédula: ", "Cédula Cliente", JOptionPane.QUESTION_MESSAGE);
            if (cedula == null || cedula.isEmpty()) {
                continue;
            }

            nombre = JOptionPane.showInputDialog(null, "Nombre: ", "Nombre Cliente", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null || nombre.isEmpty()) {
                continue;
            }

            apellido = JOptionPane.showInputDialog(null, "Apellido: ", "Apellido Cliente",
                    JOptionPane.QUESTION_MESSAGE);
            if (apellido == null || apellido.isEmpty()) {
                continue;
            }

            telefono = JOptionPane.showInputDialog(null, "Teléfono: ", "Teléfono Cliente",
                    JOptionPane.QUESTION_MESSAGE);
            if (telefono == null || telefono.isEmpty()) {
                continue;
            }

            direccion = JOptionPane.showInputDialog(null, "Dirección: ", "Dirección Cliente",
                    JOptionPane.QUESTION_MESSAGE);
            if (direccion == null || direccion.isEmpty()) {
                continue;
            }

            datosCompletos = true;
        }

        return new Cliente(cedula, nombre, apellido, telefono, direccion);
    }

    public static void ventaAuto() {
        if (clienteActual != null) {
            String tipo = JOptionPane.showInputDialog(null, "Ingrese el tipo de Auto a comprar: "
                    + "\n- SUV \n- Deportivo\n- Pickup\n- Sedan", "Tipo auto",
                    JOptionPane.QUESTION_MESSAGE);

            Lista<Auto> resultadosTipo = concesionario.generarListadoPorPreferencias(clienteActual, tipo, null, 0,
                    null);
            if (resultadosTipo.estaVacia()) {
                JOptionPane.showMessageDialog(null,
                        "No se encontraron Autos que coincidan con los criterios de búsqueda.");
                return;
            }

            Lista<String> marcasDisponibles = concesionario.obtenerMarcasDisponibles(resultadosTipo);
            String[] marcasArray = new String[marcasDisponibles.cantidad];
            Nodo<String> actual = marcasDisponibles.primero;
            int index = 0;
            while (actual != null) {
                marcasArray[index++] = actual.dato;
                actual = actual.siguiente;
            }
            String marcaSeleccionada = (String) JOptionPane.showInputDialog(null,
                    "Seleccione la marca de Auto que desea:", "Selección de Marca",
                    JOptionPane.QUESTION_MESSAGE, null, marcasArray, marcasArray[0]);

            Lista<Auto> resultados = concesionario.generarListadoPorPreferencias(clienteActual, tipo, marcaSeleccionada,
                    0, null);

            if (resultados.cantidad > 0) {
                StringBuilder mensaje = new StringBuilder("Resultados de la búsqueda:\n\n");
                Nodo<Auto> actualMarca = resultados.primero;
                int contador = 1;

                while (actualMarca != null) {
                    mensaje.append(contador).append(": ").append(actualMarca.dato.toString()).append("\n");
                    actualMarca = actualMarca.siguiente;
                    contador++;
                }

                String opcionAutoString = JOptionPane.showInputDialog(null,
                        mensaje.toString() + "Ingrese el número del auto que desea comprar: ", "Compra de Auto",
                        JOptionPane.QUESTION_MESSAGE);

                try {
                    int opcionAuto = Integer.parseInt(opcionAutoString);
                    Nodo<Auto> nodoAuto = resultados.buscar(opcionAuto - 1);
                    if (nodoAuto != null) {
                        Auto autoVenta = nodoAuto.dato;
                        if (concesionario.registrarVenta(clienteActual, autoVenta, 0)) {
                            JOptionPane.showMessageDialog(null, "¡Venta realizada con éxito!", "Venta Realizada",
                                    JOptionPane.INFORMATION_MESSAGE);
                            autoVenta.setDisponibilidad(autoVenta.getDisponibilidad() - 1);
                            resultados.borrarPorDato(autoVenta);

                        } else {
                            JOptionPane.showMessageDialog(null, "¡No hay disponibilidad!", "Venta Rechazada",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        ;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún auto con el número especificado.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontraron autos disponibles con los criterios proporcionados.",
                        "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay cliente seleccionado.");
        }

    }

    public static void imprimirAutosComprados() {
        Lista<Auto> autosComprados = concesionario.obtenerAutosComprados();

        StringBuilder mensaje = new StringBuilder("--- Autos Vendidos ---\n\n");

        Nodo<Auto> actual = autosComprados.primero;
        float totalFacturado = 0;
        int cantidad = 0;

        while (actual != null) {
            mensaje.append(actual.dato.toString()).append("\n");
            totalFacturado += actual.dato.getPrecio();
            actual = actual.siguiente;
            cantidad++;
        }
        mensaje.append("\nTotal de Autos Vendidos: ").append(cantidad).append("\nTotal Facturado: $")
                .append(totalFacturado);

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Autos Comprados", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void buscarAuto() {
        String tipo = JOptionPane.showInputDialog(null, "Ingrese el tipo de Auto que busca:"
                + "\n- SUV \n- Deportivo\n- Pickup\n- Sedan",
                "Tipo Auto", JOptionPane.QUESTION_MESSAGE);

        Lista<Auto> resultadosTipo = concesionario.generarListadoPorPreferencias(clienteActual, tipo, null, 0, null);

        if (resultadosTipo.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No se encontraron Autos que coincidan con los criterios de búsqueda.");
            return;
        }

        Lista<String> marcasDisponibles = concesionario.obtenerMarcasDisponibles(resultadosTipo);

        String[] marcasArray = new String[marcasDisponibles.cantidad];
        Nodo<String> actual = marcasDisponibles.primero;
        int index = 0;
        while (actual != null) {
            marcasArray[index++] = actual.dato;
            actual = actual.siguiente;
        }

        String marcaSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Seleccione la marca de Auto que desea:", "Selección de Marca",
                JOptionPane.QUESTION_MESSAGE, null, marcasArray, marcasArray[0]);

        Lista<Auto> resultadosMarca = concesionario.generarListadoPorPreferencias(clienteActual, tipo,
                marcaSeleccionada, 0,
                null);
        if (resultadosMarca.cantidad > 0) {
            StringBuilder mensaje = new StringBuilder("Autos disponibles de la marca " + marcaSeleccionada + ":\n");
            Nodo<Auto> actualMarca = resultadosMarca.primero;
            while (actualMarca != null) {
                mensaje.append(actualMarca.dato.toString()).append("\n");
                actualMarca = actualMarca.siguiente;
            }
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Autos Disponibles",
                    JOptionPane.INFORMATION_MESSAGE);
            concesionario.registrarIntereses(resultadosMarca, clienteActual);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se encontraron autos disponibles de la marca " + marcaSeleccionada
                            + " con los criterios proporcionados.",
                    "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void imprimirInteresados() {
        String tipo = JOptionPane.showInputDialog(null, "Ingrese el tipo de Auto que busca:"
                + "\n- SUV \n- Deportivo\n- Pickup\n- Sedan",
                "Tipo Auto", JOptionPane.QUESTION_MESSAGE);

        // Obtener la lista de autos disponibles del tipo seleccionado
        Lista<Auto> resultadosTipo = concesionario.generarListadoPorPreferencias(clienteActual, tipo, null, 0, null);

        if (resultadosTipo.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No se encontraron Autos que coincidan con los criterios de búsqueda.");
            return;
        }

        Lista<String> marcasDisponibles = concesionario.obtenerMarcasDisponibles(resultadosTipo);

        String[] marcasArray = new String[marcasDisponibles.cantidad];
        Nodo<String> actual = marcasDisponibles.primero;
        int index = 0;
        while (actual != null) {
            marcasArray[index++] = actual.dato;
            actual = actual.siguiente;
        }

        String marcaSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Seleccione la marca de Auto que desea:", "Selección de Marca",
                JOptionPane.QUESTION_MESSAGE, null, marcasArray, marcasArray[0]);

        Lista<Auto> resultadosMarca = concesionario.generarListadoPorPreferencias(clienteActual, tipo,
                marcaSeleccionada, 0, null);

        concesionario.imprimirInteresados(resultadosMarca);

    }

    private static void generarInformeCliente() {
        if (clienteActual != null) {
            String informe = concesionario.generarInformeCliente(clienteActual);
            JOptionPane.showMessageDialog(null, informe);

        } else {
            JOptionPane.showMessageDialog(null, "No hay cliente seleccionado.");
        }
    }

    private static void imprimirInformeVentasPorRango() {
        String fechaInicialString = JOptionPane.showInputDialog(null,
                "Ingrese la fecha inicial (formato: yyyy-MM-dd):");
        String fechaFinalString = JOptionPane.showInputDialog(null, "Ingrese la fecha final (formato: yyyy-MM-dd):");

        // Convertir las cadenas de texto a objetos LocalDate
        LocalDate fechaInicial = null;
        LocalDate fechaFinal = null;
        try {
            fechaInicial = LocalDate.parse(fechaInicialString);
            fechaFinal = LocalDate.parse(fechaFinalString);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null,
                    "Formato de fecha incorrecto. Por favor, ingrese la fecha en formato yyyy-MM-dd.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al método para generar el informe de ventas por rango de fechas
        String informe = concesionario.generarInformeVentasPorRango(fechaInicial, fechaFinal);

        // Mostrar el informe al usuario
        JOptionPane.showMessageDialog(null, informe, "Informe de Ventas", JOptionPane.INFORMATION_MESSAGE);
    }

}
