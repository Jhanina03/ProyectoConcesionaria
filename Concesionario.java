
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Concesionario {

    protected Lista<Auto> autos;
    protected Lista<Cliente> clientes;
    protected Lista<Interesado> interesados;
    private Lista<Venta> ventas;
    protected AdminSingleton admin;
    

    public Concesionario() {
        this.autos = new Lista<>();
        this.clientes = new Lista<>();
        this.interesados = new Lista<>();
        this.ventas = new Lista<>();
        this.admin = AdminSingleton.obtenerInstancia();
    }

    public Lista<Auto> obtenerAutosComprados() {
        Lista<Auto> autosComprados = new Lista<>();
        Nodo<Venta> actual = ventas.primero;

        while (actual != null) {
            autosComprados.insertarPorUltimo(actual.dato.getAuto());
            actual = actual.siguiente;
        }

        return autosComprados;
    }

    public void agregarAuto(Auto auto) {
        autos.insertarPorUltimo(auto);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.insertarPorUltimo(cliente);
    }

    public void agregarInteres(Interesado interesado) {
        interesados.insertarPorUltimo(interesado);
    }

    public void agregarVenta(Venta venta) {
        ventas.insertarPorUltimo(venta);
        borrarInteresado(venta);
    }

    // Métodos para consultar autos

    public Lista<Auto> consultarPorTipo(String tipo) {
        Lista<Auto> vehiculosPorTipo = new Lista<>();
        Nodo<Auto> actual = autos.primero;
        while (actual != null) {
            if (actual.dato.getTipo().equalsIgnoreCase(tipo)) {
                vehiculosPorTipo.insertarPorUltimo(actual.dato);
            }
            actual = actual.siguiente;
        }
        return vehiculosPorTipo;
    }

    public Lista<String> obtenerMarcasDisponibles(Lista<Auto> autos) {
        Lista<String> marcas = new Lista<>();
        Nodo<Auto> actual = autos.primero;
        while (actual != null) {
            if (!marcas.contiene(actual.dato.getMarca())) {
                marcas.insertarPorUltimo(actual.dato.getMarca());
            }
            actual = actual.siguiente;
        }
        return marcas;
    }
    
    
    public Lista<Auto> generarListadoPorPreferencias(Cliente cliente, String tipo, String marca, int anio,
            String color) {
        Lista<Auto> listadoFiltrado = new Lista<>();

        Nodo<Auto> actual = autos.primero;
        while (actual != null) {

            if ((tipo == null || actual.dato.getTipo().equalsIgnoreCase(tipo)) &&
                    (marca == null || actual.dato.getMarca().equalsIgnoreCase(marca)) &&
                    (anio == 0 || actual.dato.getAnio() == anio) &&
                    (color == null || actual.dato.getColor().equalsIgnoreCase(color))) {
                listadoFiltrado.insertarPorUltimo(actual.dato);
            }
            actual = actual.siguiente;
        }

        return listadoFiltrado;
    }

    public void consultarAnio(Cliente cliente, int anio) {
        for (Nodo<Auto> actual = autos.primero; actual != null; actual = actual.siguiente) {
            if (actual.dato.getAnio() == anio) {
                registrarInteres(cliente, actual.dato);
            }
        }
    }

    public void consultarMarca(Cliente cliente, String marca) {
        for (Nodo<Auto> actual = autos.primero; actual != null; actual = actual.siguiente) {
            if (actual.dato.getMarca().equalsIgnoreCase(marca)) {
                registrarInteres(cliente, actual.dato);
            }
        }
    }

    public void consultarColor(Cliente cliente, String color) {
        for (Nodo<Auto> actual = autos.primero; actual != null; actual = actual.siguiente) {
            if (actual.dato.getColor().equalsIgnoreCase(color)) {
                registrarInteres(cliente, actual.dato);
            }
        }
    }
    
    public void registrarIntereses(Lista<Auto> autos, Cliente cliente) {
        Nodo<Auto> actual = autos.primero;
        while (actual != null) {
            registrarInteres(cliente, actual.dato);
            actual = actual.siguiente;
        }
    }

    public Cliente buscarClienteCedula(String cedula){
        Nodo<Cliente> cliente = clientes.primero;
        while (cliente != null) {
            if (cliente.dato.getCedula().equals(cedula)) {
                return cliente.dato;
            }
            cliente = cliente.siguiente;
        }
        return null;

    }

    public void registrarInteres(Cliente cliente, Auto auto) {
        Nodo<Interesado> interesado = interesados.primero;
        Interesado nuevoInteres = new Interesado(cliente, auto, LocalDateTime.now());
        
        while (interesado != null) {
            if (interesado.dato.getCliente().equals(cliente) && interesado.dato.getAuto().getMarca().equals(auto.getMarca())) {
                return;
            }
            interesado = interesado.siguiente;
        }
        agregarInteres(nuevoInteres);
    }


    public boolean registrarVenta(Cliente cliente, Auto auto, float precio) {
        if (auto.getDisponibilidad() > 0) {
            auto.setDisponibilidad(auto.getDisponibilidad() - 1);
            Venta venta = new Venta(cliente, auto, LocalDate.now(), precio);
            agregarVenta(venta);
            return true;
        } else {
            return false;
        }

    }

    public void notificarVentas(Cliente cliente){
        Nodo<Interesado> interesado = interesados.primero;
        while (interesado != null) {
            if (interesado.dato.getCliente().equals(cliente)) {
                Nodo<Venta> venta = ventas.primero;
                while (venta != null) {
                    if (interesado.dato.getAuto().getMarca().equals(venta.dato.getAuto().getMarca()) 
                         && interesado.dato.getAuto().getTipo().equals(venta.dato.getAuto().getTipo())) {
                        interesado.dato.actualizarVenta(venta.dato);
                    }
                    venta = venta.siguiente;
                }
            }
            interesado = interesado.siguiente;
        }
    }

    public String generarInformeCliente(Cliente cliente) {
        StringBuilder informe = new StringBuilder();

        informe.append("INFORMACIÓN CLIENTE:\n");
        informe.append(cliente).append("\n\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Buscar intereses del cliente
        informe.append("Intereses del Cliente:\n");
        boolean tieneIntereses = false;
        Nodo<Interesado> nodoInteresado = interesados.primero;
        while (nodoInteresado != null) {
            if (nodoInteresado.dato.getCliente().equals(cliente)) {
                informe.append("- Vehículo: " + "\n").append(nodoInteresado.dato.getAuto().toString()).append("\n")
                        .append("- Fecha: " + "\n" + nodoInteresado.dato.getFechaHora().format(formatter)).append("\n");
                tieneIntereses = true;
            }
            nodoInteresado = nodoInteresado.siguiente;
        }

        if (!tieneIntereses) {
            informe.append("El Cliente no tiene Intereses Registrados.\n");
        }

        // Verificar si ha realizado compras
        informe.append("\nCompras del Cliente :\n");
        boolean haComprado = false;
        Nodo<Venta> nodoVenta = ventas.primero;
        while (nodoVenta != null) {
            if (nodoVenta.dato.getCliente().equals(cliente)) {
                haComprado = true;
                informe.append("- Vehículo: " + "\n").append(nodoVenta.dato.getAuto().toString()).append("\n")
                        .append("- Fecha: " + "\n" + nodoVenta.dato.getFechaHora()).append("\n");
            }
            nodoVenta = nodoVenta.siguiente;
        }

        if (!haComprado) {
            informe.append("\nEl cliente no ha realizado compras.\n");
        }

        return informe.toString();
    }

    public String generarInformeVentasPorRango(LocalDate fechaInicial, LocalDate fechaFinal) {
        StringBuilder informe = new StringBuilder();
        informe.append("INFORME DE VENTAS POR RANGO DE FECHAS:\n");
    
        // Verificar si las fechas ingresadas son válidas
        if (fechaInicial == null || fechaFinal == null || fechaInicial.isAfter(fechaFinal)) {
            informe.append("Las fechas ingresadas son inválidas.\n");
            return informe.toString();
        }
    
        // Buscar ventas realizadas dentro del rango de fechas especificado
        boolean ventasEncontradas = false;
        Nodo<Venta> venta = ventas.primero;
        while (venta != null) {
            LocalDate fechaVenta = venta.dato.getFechaHora();
            if (!fechaVenta.isBefore(fechaInicial) && !fechaVenta.isAfter(fechaFinal)) {
                if (!ventasEncontradas) {
                    informe.append("Ventas realizadas entre ").append(fechaInicial).append(" y ").append(fechaFinal).append(":\n");
                    ventasEncontradas = true;
                }
                informe.append("- Auto vendido:\n").append(venta.dato.getAuto().toString()).append("\n")
                       .append("- Fecha de venta:\n").append(venta.dato.getFechaHora()).append("\n\n");
            }
            venta = venta.siguiente;
        }
    
        // Si no se encontraron ventas en el rango de fechas especificado
        if (!ventasEncontradas) {
            informe.append("No se encontraron ventas realizadas entre ").append(fechaInicial).append(" y ").append(fechaFinal).append(".\n");
        }
    
        return informe.toString();
    }

    public void borrarInteresado(Venta venta) {
        Nodo<Interesado> interesado = interesados.primero;
        LocalDate ahora = LocalDate.now();
        while (interesado != null) {
                if (interesado.dato.getAuto().equals(venta.getAuto()) && venta.getFechaHora().plusYears(3).isBefore(ahora)) {
                    interesados.borrarPorDato(interesado.dato);
                }
            interesado = interesado.siguiente;
            
        }
    }
    public void imprimirInteresados(Lista<Auto> resultadosMarca) {
        Nodo<Auto> actual = resultadosMarca.primero;
        int contador=0;
        while (actual != null) {
            Nodo<Interesado> interesado = interesados.primero;

            while ( interesado != null ) {
                if (interesado.dato.getAuto().equals(actual.dato)) {
                    interesados.imprimirPorDato(interesado.dato);
                    contador++;
                }
                interesado = interesado.siguiente;
            }
            actual = actual.siguiente;
        }
        if (contador == 0) {
            JOptionPane.showMessageDialog(null, "No hay interesados para este modelo");
        }
    }
    

}
