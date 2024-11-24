
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Interesado implements Observer {
    private Cliente cliente;
    private Auto auto;
    private LocalDateTime fechaHora;

    public Interesado(Cliente cliente, Auto auto, LocalDateTime fechaHora) {
        this.cliente = cliente;
        this.auto = auto;
        this.fechaHora = fechaHora;
    }

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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaFormateada = fechaHora.format(formatter);
        return "Interes:\n" +
                "Cliente: " + cliente +
                "\nAuto: " + auto.getMarca() + " " + auto.getTipo() +
                "\nFecha: " + fechaFormateada;
    }

    @Override
    public void actualizarVenta(Venta venta) {
        JOptionPane.showMessageDialog(null, "Notificación de venta para el cliente: " 
                    + cliente.getNombre()+"\nSe ha vendido el siguiente auto:\n" + venta.getAuto()
                    +"\nFecha de la venta: " + venta.getFechaHora(), null, 0);

    }
}
