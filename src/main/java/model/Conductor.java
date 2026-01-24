package model;

public class Conductor {
    // He cambiado los atributos a private para seguir la encapsulación básica en Java
    // (has pedido un cambio sencillo, sin añadidos complejos como JavaDoc o políticas de reintentos).
    private int numeroConductor;
    private String nombre;
    private String apellidos;

    // Constructor público: sigue igual en uso, ahora asigna valores a los atributos privados.
    public Conductor(int numeroConductor ,String nombre, String apellido) {
        this.nombre = nombre;
        this.apellidos = apellido;
        this.numeroConductor = numeroConductor;
    }

    // Getters y setters: permanecen para permitir el acceso y la modificación de los atributos privados.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getNumeroConductor() {
        return numeroConductor;
    }

    public void setNumeroConductor(int numeroConductor) {
        this.numeroConductor = numeroConductor;
    }

    @Override
    public String toString() {
        return "\n\tNumeroConductor: " + numeroConductor +
                "\n\tNombre: " + nombre +
                "\n\tApellidos: " + apellidos;
    }

    // Comentario final: cambios realizados:
    // - Atributos cambiados a private para encapsulación..
    // - Getters y setters mantenidos para que otras partes del proyecto sigan funcionando.
}
