package app;

public class Conductor {
    public String nombre;
    public String apellido;
    public int numeroConductor;

    public Conductor(String nombre, String apellido, int numeroConductor) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroConductor = numeroConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumeroConductor() {
        return numeroConductor;
    }

    public void setNumeroConductor(int numeroConductor) {
        this.numeroConductor = numeroConductor;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroConductor=" + numeroConductor +
                '}';
    }
}
