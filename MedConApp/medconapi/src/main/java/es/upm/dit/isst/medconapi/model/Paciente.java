package es.upm.dit.isst.medconapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    private String cipa;
    private String nombre;
    private String apellidos;
    private String id_espera;
    // para indicar si está presente 
    private int status;

    public Paciente() {}

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
    public String getApellido() {
        return apellidos;
    }
    public void setApellido(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getId_espera() {
        return id_espera;
    }
    public void setId_espera(String id_espera) {
        this.id_espera = id_espera;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }      
}
