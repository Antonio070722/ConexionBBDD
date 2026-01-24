package model;

public class Bus {
    private int Registro;
    private String Licencia;
    private String Tipo;

    public Bus(int registro, String licencia, String tipo) {
        Registro = registro;
        Licencia = licencia;
        Tipo = tipo;
    }

    public int getRegistro() {
        return Registro;
    }

    public void setRegistro(int registro) {
        Registro = registro;
    }

    public String getLicencia() {
        return Licencia;
    }

    public void setLicencia(String licencia) {
        Licencia = licencia;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "Registro=" + Registro +
                ", Licencia='" + Licencia + '\'' +
                ", Tipo='" + Tipo + '\'' +
                '}';
    }
}
