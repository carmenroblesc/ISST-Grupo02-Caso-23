package es.upm.dit.isst.medconweb.model;

public class Medico {
    private String nColegiado;
    private String nombre;
    private String apellidos;
    private String especialidad;

    //Constructores
    public Medico() {}

    public Medico(String nColegiado, String nombre, String apellidos, String especialidad) {
        this.nColegiado = nColegiado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
    }

    //Getters & Setters
    public String getNColegiado() {
        return nColegiado;
    }

    public void setNColegiado(String nColegiado) {
        this.nColegiado = nColegiado;
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
        result = prime * result + ((nColegiado == null) ? 0 : nColegiado.hashCode());
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
        if (nColegiado == null) {
            if (other.nColegiado != null)
                return false;
        } else if (!nColegiado.equals(other.nColegiado))
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
        return "Medico [nColegiado=" + nColegiado + ", nombre=" + nombre + ", apellidos=" + apellidos
                + ", especialidad=" + especialidad + "]";
    }  
    
    
}
