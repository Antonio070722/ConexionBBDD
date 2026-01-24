package model;

public class Lugar {
    private int IdLugar;
    private String site;
    private String cp;
    private String ciudad;

    public Lugar(int IdLugar, String site, String cp, String ciudad) {
        this.IdLugar = IdLugar;
        this.site = site;
        this.cp = cp;
        this.ciudad = ciudad;
    }

    public int getIdLugar() {
        return IdLugar;
    }

    public void setIdLugar(int idLugar) {
        IdLugar = idLugar;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "\n\tIdLugar: " + IdLugar +
                "\n\tSite: " + site +
                "\n\tCP: " + cp +
                "\n\tCiudad: " + ciudad;
    }
}
