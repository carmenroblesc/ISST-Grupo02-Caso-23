package es.upm.dit.isst.medconweb.model;

public class Paciente {
    private String cipa;
    private String nombre;
    private String apellidos;
    private String idEspera;
    // para indicar si está presente o no 
    private Boolean presente;
 
    //Constructores
    public Paciente() {}

    public Paciente(String cipa, String nombre, String apellidos, String idEspera, Boolean presente) {
        this.cipa = cipa;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.idEspera = idEspera;
        this.presente = presente;
    }

    //Getters & Setters
    public String getCipa() {
        return cipa;
    }
    public void setCipa(String cipa) {
        this.cipa = cipa;
    }
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
    public String getIdEspera() {
        return idEspera;
    }
    public void setIdEspera(String idEspera) {
        this.idEspera = idEspera;
    }
    public Boolean getPresente() {
        return presente;
    }
    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    //hashCode & equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cipa == null) ? 0 : cipa.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
        result = prime * result + ((idEspera == null) ? 0 : idEspera.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Paciente other = (Paciente) obj;
        if (cipa == null) {
            if (other.cipa != null)
                return false;
        } else if (!cipa.equals(other.cipa))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (apellidos == null) {
            if (other.apellidos != null)
                return false;
        } else if (!apellidos.equals(other.apellidos))
            return false;
        if (idEspera == null) {
            if (other.idEspera != null)
                return false;
        } else if (!idEspera.equals(other.idEspera))
            return false;
        if (presente != other.presente)
            return false;
        return true;
    }

    //toString
    @Override
    public String toString() {
        return "Paciente [cipa=" + cipa + ", nombre=" + nombre + ", apellidos=" + apellidos + ", idEspera=" + idEspera
                + ", presente=" + presente + "]";
    }      

    
}