package es.upm.dit.isst.medconapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    private String n_colegiado;
    private String nombre;
    private String apellidos;
    private String especialidad;

    public Medico() {}

    public String getN_colegiado() {
        return n_colegiado;
    }

    public void setN_colegiado(String n_colegiado) {
        this.n_colegiado = n_colegiado;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    
    
}
