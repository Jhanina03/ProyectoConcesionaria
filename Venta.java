import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Venta {
    private Cliente cliente;
    private Auto auto;
    private LocalDate fechaHora;
    private float precio;

    public Venta(Cliente cliente, Auto auto, LocalDate fechaHora, float precio) {
        this.cliente = cliente;
        this.auto = auto;
        this.fechaHora = fechaHora;
        this.precio = precio;
    }

    // Métodos de acceso y modificación

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void mostrarDetallesVenta() {
        String detallesVenta = this.toString();
        JOptionPane.showMessageDialog(null, detallesVenta, "Detalles de la Venta", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public String toString() {
        return "Venta\n" +
                "auto=" + this.getAuto() +
                " - cliente=" + this.getCliente() +
                " - fechaVenta=" + this.getFechaHora() +
                "\n - precioFacturacion=" +  +this.precio+
                '\n';
    
    }
}

