import javax.swing.JOptionPane;

public class Lista<T> {
    
    Nodo<T> primero;
    int cantidad;

    public Lista() {
        this.primero = null;
        this.cantidad = 0;
    }

    public boolean insertarPorPrimero(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato, null);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            nuevo.siguiente = this.primero;
            this.primero = nuevo;
        }
        this.cantidad++;
        return true;
    }

    public boolean insertarPorUltimo(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato, null);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            Nodo<T> ultimo = this.buscar(this.cantidad - 1);
            ultimo.siguiente = nuevo;
        }
        this.cantidad++;
        return true;
    }
    
    public boolean insertar(T dato, int pos){
        if (pos < 0 || pos > this.cantidad || dato == null)
            return false;
        if (pos == 0)
            return insertarPorPrimero(dato);
        if (pos == this.cantidad)
            return insertarPorUltimo(dato);
        Nodo<T> anterior = this.buscar(pos-1);
        anterior.siguiente = new Nodo<>(dato, anterior.siguiente);
        this.cantidad++;
        return true;
    }

    public Nodo<T> buscar(int pos) {
        if (pos < 0 || pos >= this.cantidad)
            return null;
        int posActual = 0;
        Nodo<T> actual = this.primero;
        while (posActual != pos) {
            actual = actual.siguiente;
            posActual++;
        } 
        return actual;
    }
    
    public boolean borrar(int pos){
        if (pos < 0 || pos >= this.cantidad)
            return false;
        if (pos == 0) {
            this.primero = this.primero.siguiente;
            this.cantidad--;
            return true;
        }
        Nodo<T> anterior = this.buscar(pos - 1);
        if (anterior == null || anterior.siguiente == null)
            return false;
        anterior.siguiente = anterior.siguiente.siguiente;
        this.cantidad--;
        return true;
    }
    
    public boolean borrarPorDato(T dato){
        boolean borrado = false;
        while (this.primero != null && this.primero.dato.equals(dato)){
            this.primero = this.primero.siguiente;
            this.cantidad--;
            borrado = true;
        }
        Nodo<T> actual = this.primero;
        while (actual != null && actual.siguiente != null){
            if (actual.siguiente.dato.equals(dato)){
                actual.siguiente = actual.siguiente.siguiente;
                this.cantidad--;
                borrado = true;
            } else {
                actual = actual.siguiente;
            }
        }
        return borrado;
    }
    public void imprimir() {
        Nodo<T> actual = primero;
        StringBuilder mensaje = new StringBuilder();
        while (actual != null) {
            mensaje.append(actual.dato.toString()).append("\n");
            actual = actual.siguiente;
        }
        JOptionPane.showMessageDialog(null, mensaje.toString(), null, cantidad);
    }
    
    public boolean estaVacia() {
        return primero == null;
    }

    public boolean contiene(T dato) {
        Nodo<T> actual = primero;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    public void imprimirPorDato(T dato) {
        StringBuilder mensaje = new StringBuilder();
        Nodo<T> actual = primero;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                mensaje.append(actual.dato.toString()).append("\n");
            }
            actual = actual.siguiente;
        }
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Elementos con el dato: " + dato.toString(), JOptionPane.INFORMATION_MESSAGE);
    }
}