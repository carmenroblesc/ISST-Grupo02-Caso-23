package es.upm.dit.isst.medconweb.model;

public class Medico {
    private String ncolegiado;
    private String nombre;
    private String apellidos;
    private String especialidad;

    //Constructores
    public Medico() {}

    //Getters & Setters
    public String getNcolegiado() {
        return ncolegiado;
    }

    public void setNcolegiado(String nColegiado) {
        this.ncolegiado = nColegiado;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    //hashCode & equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ncolegiado == null) ? 0 : ncolegiado.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
        result = prime * result + ((especialidad == null) ? 0 : especialidad.hashCode());
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
        Medico other = (Medico) obj;
        if (ncolegiado == null) {
            if (other.ncolegiado != null)
                return false;
        } else if (!ncolegiado.equals(other.ncolegiado))
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
        if (especialidad == null) {
            if (other.especialidad != null)
                return false;
        } else if (!especialidad.equals(other.especialidad))
            return false;
        return true;
    }


    //toString
    @Override
    public String toString() {
        return "Medico [ncolegiado=" + ncolegiado + ", nombre=" + nombre + ", apellidos=" + apellidos
                + ", especialidad=" + especialidad + "]";
    }  
    
    
}
