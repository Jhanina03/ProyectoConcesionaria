
public class Auto {

    private String placa;
    private String marca;
    private String color;
    private int anio;
    private String tipo;
    private float precio;
    private int disponibilidad;

    public Auto(String placa, String marca, String color, int anio, String tipo, float precio, int disponibilidad) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.anio = anio;
        this.tipo = tipo;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAnio() {
        return this.anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getDisponibilidad() {
        return this.disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return "Auto: " + this.getMarca()+ " " + this.getTipo()+
                " - Color: " + this.getColor() +
                " - Precio: $" + this.getPrecio();
    }
}
