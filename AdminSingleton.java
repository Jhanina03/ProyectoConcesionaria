public class AdminSingleton {
    private static AdminSingleton instancia;

    private String nombre;
    private String cedula;

    private AdminSingleton() {
        nombre = "admin";
        cedula = "admin123";
    }


    public static AdminSingleton obtenerInstancia() {
        if (instancia == null) {
            instancia = new AdminSingleton();
        }
        return instancia;
    }

    public boolean verificarCredenciales(String nombre, String cedula) {
        return this.nombre.equals(nombre) && this.cedula.equals(cedula);
    }
    
}
